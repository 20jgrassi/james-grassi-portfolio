var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
//1000x500 canvas

const origin = { x: 0, y: 0 };

var camera = {
  focal: origin, //point in space or Celestial Body to center camera on by default
  offset: { x: 0, y: 0 }, //offset from focal point of camera center, changed by panning the camera.

  center: function () {
    return {
      x: this.focal.x + this.offset.x,
      y: this.focal.y + this.offset.y
    };
  },

  scale: m(35e5) //m per pixel
};

var timeElapsed = {
  days: 0, years: 0,
  total: function () {
    return this.days + 365.25 * this.years
  }
}; //how much simulated time has elapsed

var period = { days: 0.5 }; //period.days is how many days to simulate at once, per calculation. the larger the number, the faster the program can be, but the less accurate it is.

const bigG = 6.6743e-11; //gravitational constant

//daysToSeconds(days:number): number
function daysToSeconds(days) {
  return days * 24 * 60 * 60;
}

//secondsToDays(seconds:number): number
function secondsToDays(seconds) {
  return seconds / 60 / 60 / 24;
}

//m(km:number/Vector):number/Vector
function m(km) {
  return typeof (km) === "number" ?
    km * 1000 :
    { x: km.x * 1000, y: km.y * 1000 };
}

//class that represents a Planet, Star, Moon, Comet, etc.
class CelestialBody {
  constructor(name, mass, position, velocity, bigness, color) {
    this.name = name; //string
    this.mass = mass; //number (kg)
    this.p = position; //Vector (m)
    this.v = velocity; //Vector (m/s)
    this.bigness = bigness; //number (px), radius of dot on display
    this.color = color; //color string
  }
} //all units SI

//distance(a: CelestialBody, b:CelestialBody): number
function distance(a, b) {
  return Math.sqrt(
    Math.pow((b.p.x - a.p.x), 2) +
    Math.pow((b.p.y - a.p.y), 2));
}

//distance(a: CelestialBody, b:CelestialBody): number
function angle(a, b) {
  return Math.atan2((b.p.y - a.p.y), (b.p.x - a.p.x)); //radians
}

//accelerate(space: CelestialBody[], period: number): void
function accelerate(space, period) {
  var dist; var angl; var accel;

  space.forEach((body) => {
    space.forEach((other) => {
      if (body !== other) {
        dist = distance(body, other);
        angl = angle(body, other);
        accel = ((bigG * other.mass) / Math.pow(dist, 2)) * daysToSeconds(period);
        //alter body.v.x and body.v.y
        body.v.x += accel * Math.cos(angl);
        body.v.y += accel * Math.sin(angl);
      }
    });
  });
}
//g = GM/r^2

//reposition(space: CelestialBody[], period:number): void
function reposition(space, period) {
  space.forEach((body) => {
    //alter body.p.x and body.p.y
    body.p.x += body.v.x * daysToSeconds(period);
    body.p.y += body.v.y * daysToSeconds(period);
  });
}

//observe(space: CelestialBody[], period: number): void
function observe(space) {
  var p;

  function getDrawCoords(body) {
    return {
      x: (body.p.x - camera.center().x) / camera.scale + c.width / 2,
      y: -(body.p.y - camera.center().y) / camera.scale + c.height / 2
    };
  }
  //converts simulated coordinates (m, cartesian)
  //...to canvas coordinates (px, top-left origin)

  ctx.clearRect(0, 0, c.width, c.height); //clear previous frame

  space.forEach((body) => {
    p = getDrawCoords(body);

    ctx.fillStyle = body.color;
    ctx.beginPath();
    ctx.arc(p.x, p.y, body.bigness, 0, 2 * Math.PI);
    ctx.fill();
    //draw a circle of the correct color and bigness
  });
}

//simulate(space: CelestialBody[]): void
function simulate(space, period) {
  accelerate(space, period.days);
  reposition(space, period.days);
  observe(space);
  timeElapsed.days += period.days;
} //master function that calls all others

/*
space refers to the CelestialBody[] that contains the system of celestial bodies that will be simulated.

period refers to how long, in days, the program should simulate at a time. velocity and position are updated all at once based on the bodies' information at the start of the period, so entering too long of a span risks the simulation drifitng out of touch with reality.
*/

var solarSystem = [
  new CelestialBody("Sun", 1988500e24,
    m({ x: 0, y: 0 }),
    m({ x: 0, y: 0 }),
    5, "yellow"),

  new CelestialBody("Mercury", 0.33011e24,
    m({ x: 69.817e6, y: 0 }),
    m({ x: 0, y: 38.86 }),
    3, "gray"),

  new CelestialBody("Venus", 4.8675e24,
    m({ x: 108.939e6, y: 0 }),
    m({ x: 0, y: 34.79 }),
    3, "peachpuff"),

  new CelestialBody("Earth", 5.9724e24,
    m({ x: 152.099e6, y: 0 }),
    m({ x: 0, y: 29.29 }),
    3, "seagreen"),


  new CelestialBody("Moon", 0.07346e24,
    m({ x: 152.099e6 + 0.4055e6, y: 0 }),
    m({ x: 0, y: 29.29 + 0.970 }),
    1, "cornsilk"),


  new CelestialBody("Mars", 0.64171e24,
    m({ x: 249.229e6, y: 0 }),
    m({ x: 0, y: 21.97 }),
    3, "red"),

  new CelestialBody("Jupiter", 1898.19e24,
    m({ x: 816.618e6, y: 0 }),
    m({ x: 0, y: 12.44 }),
    4, "orange"),

  new CelestialBody("Saturn", 568.34e24,
    m({ x: 1514.504e6, y: 0 }),
    m({ x: 0, y: 9.09 }),
    4, "lemonchiffon"),

  new CelestialBody("Uranus", 86.813e24,
    m({ x: 3003.625e6, y: 0 }),
    m({ x: 0, y: 6.49 }),
    4, "skyblue"),

  new CelestialBody("Neptune", 102.413e24,
    m({ x: 4545.671e6, y: 0 }),
    m({ x: 0, y: 5.37 }),
    4, "darkblue")
];

solarSystem = solarSystem.reverse(); //better for display

/*
Info sourced from:
https://nssdc.gsfc.nasa.gov/planetary/factsheet/earthfact.html

all bodies begin the simulation positioned at their aphelia/apogees
*/

setInterval(simulate, 10, solarSystem, period);
//defaults: 10, 0.5

//for mass randomizer
function massRandomize() {
  function funNumber() {
    return Math.random() *
      Math.pow(
        10,
        24 + Math.random() * 8
      ); //24, 8
  }

  solarSystem.forEach((body) => {
    body.mass = funNumber();
  });

  document.body.style.backgroundColor = "white";
  setInterval(function () {
    document.body.style.backgroundColor = "rgb(0,0,32)";
  }, 50);
}

//for geocentrism mode
const earth = solarSystem[solarSystem.length - 4].p;