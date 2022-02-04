const kama_pona = document.getElementById('kama-pona');

const sitelen_Latin = document.getElementById('sitelen-Latin');
const sitelen_pona = document.getElementById('sitelen-pona');

const linja_seme = document.getElementById('linja-seme');
const suli_seme = document.getElementById('suli-seme');
const kule_seme = document.getElementById('kule-seme');
const kule_monsi_seme = document.getElementById('kule-monsi-seme');
const poka_seme = document.getElementsByName('poka-seme');

const leko_seme_x = document.getElementById('leko-seme-x');
const leko_seme_y = document.getElementById('leko-seme-y');
const leko_seme_selo = document.getElementById('leko-seme-selo');

const poki_pi_nimi_linja = document.getElementById('poki-pi-nimi-linja');
const nimi_linja = document.getElementsByClassName('nimi-linja');

const poki_pi_sona_suli = document.getElementById('poki-pi-sona-suli');

const nimi_lipu_seme = document.getElementById('nimi-lipu-seme');

const sona_ike_lipu = document.getElementById('sona-ike-lipu');

const o_sin_e_nasin = document.getElementById('o-sin-e-nasin');
const o_awen_e_sitelen_sina = document.getElementById('o-awen-e-sitelen-sina');


sitelen_Latin.addEventListener("input", sl_sin);
function sl_sin(s) {
  sitelen_pona.innerHTML = sitelen_Latin.value;
}

sitelen_pona.addEventListener("input", sp_sin);
function sp_sin(s) {
  sitelen_Latin.value = sitelen_pona.innerHTML;
}

sitelen_Latin.addEventListener("input", lukin_ala_lukin_pi_sona_ike);
sitelen_pona.addEventListener("input", lukin_ala_lukin_pi_sona_ike);
function lukin_ala_lukin_pi_sona_ike(s) {
  let sitelen = sitelen_pona.textContent;
  if(sitelen.includes("-") || sitelen.includes("+") || sitelen.includes("_") ||
  sitelen.includes("[") || sitelen.includes("]")) {
    sona_ike_lipu.style.display = "block";
  } else {
    sona_ike_lipu.style.display = "none";
  }
}


linja_seme.addEventListener("input", linja_sin);
function linja_sin(l) {
  sitelen_pona.style.fontFamily = linja_seme.value;
}

linja_seme.addEventListener("focus", nimi_linja_lon);
function nimi_linja_lon(l) {
  poki_pi_nimi_linja.style.display = "block";
}

linja_seme.addEventListener("blur", nimi_linja_ala);
function nimi_linja_ala(l) {
  poki_pi_nimi_linja.style.display = "none";
}

suli_seme.addEventListener("input", suli_sin);
function suli_sin(s) {
  sitelen_pona.style.fontSize = suli_seme.value;
}

suli_seme.addEventListener("focus", sona_suli_lon);
leko_seme_x.addEventListener("focus", sona_suli_lon);
leko_seme_y.addEventListener("focus", sona_suli_lon);
leko_seme_selo.addEventListener("focus", sona_suli_lon);
function sona_suli_lon(l) {
  poki_pi_sona_suli.style.display = "block";
}

leko_seme_x.addEventListener("blur", sona_suli_ala);
leko_seme_y.addEventListener("blur", sona_suli_ala);
leko_seme_selo.addEventListener("blur", sona_suli_ala);
suli_seme.addEventListener("blur", sona_suli_ala);
function sona_suli_ala(l) {
  poki_pi_sona_suli.style.display = "none";
}

kule_seme.addEventListener("input", kule_sin);
function kule_sin(k) {
  sitelen_pona.style.color = kule_seme.value;
}

kule_monsi_seme.addEventListener("input", kule_monsi_sin);
function kule_monsi_sin(k) {
  sitelen_pona.style.backgroundColor = kule_monsi_seme.value;
}

for(var i = 0; i < poka_seme.length; ++i) {
    poka_seme[i].onclick = function() {
        sitelen_Latin.style.textAlign = this.value;
        sitelen_pona.style.textAlign = this.value;
    }
}

leko_seme_x.addEventListener("input", leko_sin);
leko_seme_y.addEventListener("input", leko_sin);
leko_seme_selo.addEventListener("input", leko_sin);
function leko_sin(s) {
  sitelen_pona.style.width = leko_seme_x.value;
  sitelen_pona.style.height = leko_seme_y.value;
  sitelen_pona.style.padding = leko_seme_selo.value;
}

for(var i = 0; i < nimi_linja.length; ++i) {
  nimi_linja.item(i).onmousedown = function() {
    linja_seme.value = this.textContent;
    linja_sin();
  }

  nimi_linja.item(i).onmouseover = function() {
    kama_pona.style.fontFamily = this.textContent;
  }

  nimi_linja.item(i).onmouseout = function() {
    kama_pona.style.fontFamily = "linja sike";
  }
}

o_sin_e_nasin.addEventListener("click", sin_e_nasin);
function sin_e_nasin() {
  linja_seme.value = "linja sike";
  linja_sin();

  suli_seme.value = "1em";
  suli_sin();

  kule_seme.value = "#000000";
  kule_sin();

  kule_monsi_seme.value = "#ffffff";
  kule_monsi_sin();

  document.getElementById('poka-seme-open').checked = true;
  sitelen_Latin.style.textAlign = "left";
  sitelen_pona.style.textAlign = "left";

  leko_seme_x.value = "90vw";
  leko_seme_y.value = "30vh";
  leko_seme_selo.value = "10px";
  leko_sin();

  nimi_lipu_seme.value = "sitelen kule";
}

sin_e_nasin();

// https://stackoverflow.com/questions/6887183/how-to-take-screenshot-of-a-div-with-javascript
// https://github.com/niklasvh/html2canvas/issues/838

o_awen_e_sitelen_sina.addEventListener("click", awen_e_sitelen_sina);
function awen_e_sitelen_sina() {
  html2canvas(sitelen_pona, {
      onrendered: function(canvas) {
          theCanvas = canvas;


          canvas.toBlob(function(blob) {
              saveAs(blob, nimi_lipu_seme.value + ".png");
          });
      }
  });
}
