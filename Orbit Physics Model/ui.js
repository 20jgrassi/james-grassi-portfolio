const dayCounter = document.getElementById("dayCounter");
const yearCounter = document.getElementById("yearCounter");
const timeScale = document.getElementById("timeScale");
const braheSwitch = document.getElementById("braheSwitch");
const braheBlurb = document.getElementById("braheBlurb");


window.addEventListener("keydown", onKeyDown, false);
window.addEventListener("keyup", onKeyUp, false);

function onKeyDown(event) {
  var keyCode = event.keyCode;
  switch (keyCode) {
    case 68: //d
      keyD = true;
      break;
    case 83: //s
      keyS = true;
      break;
    case 65: //a
      keyA = true;
      break;
    case 87: //w
      keyW = true;
      break;
    case 81: //q
      keyQ = true;
      break;
    case 69: //e, nice
      keyE = true;
      break;

    case 77:
      massRandomize();
      break;
  }
}

function onKeyUp(event) {
  var keyCode = event.keyCode;

  switch (keyCode) {
    case 68: //d
      keyD = false;
      break;
    case 83: //s
      keyS = false;
      break;
    case 65: //a
      keyA = false;
      break;
    case 87: //w
      keyW = false;
      break;
    case 81: //q
      keyQ = false;
      break;
    case 69: //e, nice
      keyE = false;
      break;
  }
}

var keyW = false;
var keyA = false;
var keyS = false;
var keyD = false;

var keyQ = false;
var keyE = false;

timeScale.oninput = function() {
  period.days = timeScale.value / timeScale.max;
};

function panCamera() {
  camera.scale *= keyQ ? 1.025 : 1;
  camera.scale /= keyE ? 1.025 : 1;

  camera.offset.y += keyW ? camera.scale * 5 : 0;
  camera.offset.x -= keyA ? camera.scale * 5 : 0;
  camera.offset.y -= keyS ? camera.scale * 5 : 0;
  camera.offset.x += keyD ? camera.scale * 5 : 0;
  //adjusts camera if W/A/S/D/Q/E keys held down
}

function handleFocus() {
  var brah = braheSwitch.checked;
  if (brah && camera.focal == origin) {
    camera.focal = earth;
    braheBlurb.style.display = "block";
  }
  if (!brah && camera.focal == earth) {
    camera.focal = origin;
    braheBlurb.style.display = "none";
  }
}

function countDays() {
  function pad(n) {
    if (n < 10) { return "00" + n; }
    if (n < 100) { return "0" + n; }
    return n;
  }

  if (timeElapsed.days >= 365.25) {
    timeElapsed.days -= 365.25;
    timeElapsed.years += 1;
  }

  dayCounter.innerText = pad(Math.floor(timeElapsed.days));
  yearCounter.innerText = timeElapsed.years;
}

function ui_update() {
  panCamera();
  handleFocus();
  countDays();
}

setInterval(ui_update, 10);