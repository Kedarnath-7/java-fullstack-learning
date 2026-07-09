"use strict";
;
class Employee {
    fname;
    lname;
    constructor(fname, lname) {
        this.fname = fname;
        this.lname = lname;
    }
    fullName() {
        return this.fname + " " + this.lname;
    }
    disp() {
        console.log("hi " + this.fullName());
    }
}
let e1 = new Employee("Sachin", "Tendulkar");
e1.disp();
