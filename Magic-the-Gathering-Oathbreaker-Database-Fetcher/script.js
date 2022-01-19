//All comments containing urls are source citations

//https://scryfall.com/docs/api
//https://www.taniarascia.com/how-to-connect-to-an-api-with-javascript/

global = {cards: [], resting: true, error:"none", oathbreakerList: [], signatureList: []}
banlist = [
  "Ad Nauseam",
  "Ancestral Recall", 
  "Balance",
  "Biorhythm",
  "Black Lotus",
  "Channel",
  "Chaos Orb",
  "Cleanse",
  "Crusade",
  "Dark Ritual",
  "Doomsday",
  "Emrakul, the Aeons Torn",
  "Expropriate",
  "Falling Star",
  "Fastbond",
  "Gifts Ungiven",
  "Griselbrand",
  "High Tide",
  "Imprison",
  "Invoke Prejudice",
  "Jihad",
  "Jeweled Lotus",
  "Library of Alexandria",
  "Limited Resources",
  "Lion's Eye Diamond",
  "Mana Crypt",
  "Mana Geyser",
  "Mana Vault",
  "Mox Emerald",
  "Mox Jet",
  "Mox Pearl",
  "Mox Ruby",
  "Mox Sapphire",
  "Natural Order",
  "Painter's Servant",
  "Pradesh Gypsies",
  "Primal Surge",
  "Saheeli, the Gifted",
  "Shaharazad",
  "Sol Ring",
  "Stone-Throwing Devils",
  "Sundering Titan",
  "Sylvan Primordial",
  "Time Vault",
  "Time Walk",
  "Tinker",
  "Tolarian Academy",
  "Tooth and Nail",
  "Trade Secrets",
  "Upheaval",
  "Yawgmoth's Bargain"];

//https://stackoverflow.com/questions/6274339/how-can-i-shuffle-an-array
function shuffle(a) {
    for (let i = a.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [a[i], a[j]] = [a[j], a[i]];
    }
    return a;
}

function queue(hastyFunction) {

  //Check if the program encounters an error, then unqueue all functions
  if(global.error != "none") {
    return;
  //When global.resting == true (global.cards has been fully updated), execute the function
  } else if(global.resting == true) {
    hastyFunction();
  
  //Keep trying every half second until global.resting == true or an error is encountered
  } else {
    setTimeout(queue, 500, hastyFunction);
  }
}

function scry(query) {

  //URLify the search query
  query = encodeURIComponent(query);

  url = 'https://api.scryfall.com/cards/search?q=';
  url += query;

  //Returns the full url to be passed into draw()
  return(url);
}

function draw(url) {
  //The program might change global.cards real soon, so hold up
  global.resting = false;

  //Magic codesauce that just works. Copypasted this part. url is the request
  var request = new XMLHttpRequest();
  request.open('GET', url, true);

  //Start doing this when you get that information back from Scryfall
  request.onload = function() {
    var page = JSON.parse(this.response);

    if(true) {
      global.cards = global.cards.concat(page["data"]);

      if(page["has_more"] == true) {
        //Recursive function, will get the next page, and the next, and so on
        //Need to delay 50ms so scryfall doesn't get pissed off
        setTimeout(draw(page["next_page"], 50));
      } else {
        //Signals queue() that the function is done changing the global.cards list, so it's accurate
        global.resting = true;
      }

    } else {
      
      //Error handling
      global.error = "bad search";
      displayResults();

    }
  }

  //Send the actual request
  request.send();
  return;
}

function cleanse(cardList) {

  for (i = cardList.length - 1; i >= 0; --i) {
    card = cardList[i];
    banned = false;

    if(card == undefined) {
      cardList.splice(i, 1);
      continue;
    }

    if(card["object"] != "card") {
      cardList.splice(i, 1);
      continue;
    }
    
    if(card["layout"] == "transform") {
      if(!card["card_faces"][0]["type_line"].includes("Planeswalker")) {
        banned = true;
      }
    }

    if(banlist.includes(card["name"])) {
      banned = true;
    }

    if(banned == true) {
      cardList.splice(i, 1);
    }
  }

  return(cardList);
}

function getOathbreaker() {

  oathbreakerInput = "(" + document.getElementById("oathbreakerInput").value;
  oathbreakerInput += ") f:vintage t:planeswalker";

  oathbreakerQuery = scry(oathbreakerInput);
  draw(oathbreakerQuery);
  writeResults("searching");

  //queue needed
  queue(function() {
    oathbreakerList = global.cards;
    global.cards = [];
    oathbreakerList = cleanse(oathbreakerList);
    oathbreakerList = shuffle(oathbreakerList);
    global.oathbreakerList = oathbreakerList;

    if(global.oathbreakerList.length > 0) {
      getSignature(global.oathbreakerList[0]);
    } else {
      global.error = "no valid results";
      displayResults();
    }
  });
}

function getSignature(oathbreaker) {
  obColorId = oathbreaker['color_identity'].join("");
  if(obColorId == "") {
    obColorId = "C";
  }

  signatureInput = "(" + document.getElementById("signatureInput").value;
  signatureInput += ") f:vintage (t:instant or t:sorcery) identity<=" + obColorId;

  signatureQuery = scry(signatureInput);
  draw(signatureQuery);

  //queue needed
  queue(function() {
    signatureList = global.cards;
    global.cards = [];
    signatureList = cleanse(signatureList);
    signatureList = shuffle(signatureList);
    global.signatureList = signatureList;

    if(global.signatureList.length == 0) {
      global.oathbreakerList.shift();
      if(global.oathbreakerList.length > 0) {
        getSignature(global.oathbreakerList[0]);
      } else {
        global.error = "no valid results";
        displayResults();
      }
    } else if(global.signatureList[0] == undefined) {
      global.oathbreakerList.shift();
      if(global.oathbreakerList.length > 0) {
        getSignature(global.oathbreakerList[0]);
      } else {
        global.error = "no valid results";
        displayResults();
      }
    } else {
      displayResults();
      writeResults("finished");
    }
  });
}

function displayResults() {
  if(global.error == "none") {

    o = global.oathbreakerList[0];
    s = global.signatureList[0];

    if(o["layout"] == "transform") {
      document.getElementById("oathbreakerCard").src = o["card_faces"][0]["image_uris"]["png"]
    } else {
      document.getElementById("oathbreakerCard").src = o["image_uris"]["png"];
    }

    if(s["layout"] == "transform") {
      document.getElementById("signatureCard").src = s["card_faces"][0]["image_uris"]["png"];
    } else {
      document.getElementById("signatureCard").src = s["image_uris"]["png"];
    }

  } else {
    writeResults(global.error);
  }
}

function writeResults(reason) {
  if(reason == "searching") {
    document.getElementById("contextBlurb").innerHTML = "Now searching for results. Please wait a moment."
  } else if(reason == "bad search") {
    document.getElementById("contextBlurb").innerHTML = "Error: Could not connect to scryfall. Please try again."
  } else if(reason == "no valid results") {
    document.getElementById("contextBlurb").innerHTML = "Your search does not match any valid Oathbreaker / Signature Spell pairings. Ensure that your search terms are correct and try again."
  } else if(reason == "finished") {
    document.getElementById("contextBlurb").innerHTML = "Now showing your results."
  }
}

function reset() {
  global.cards = [];
  global.resting = true;
  global.error = "none";
  global.oathbreakerList = [];
  global.signatureList = [];
}

function getResults() {
  if(global.resting == true || global.error != "none") {
    reset();
    getOathbreaker();
  }
}