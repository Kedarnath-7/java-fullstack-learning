interface saysHi{
  sayhi: Function;
};

class Employee implements saysHi{ 
    constructor(private fname:string,private lname:string) {
    }  
	fullName():string{
		return this.fname+" "+this.lname;
	}

    disp():void { 
        console.log("hi "+this.fullName()); 
    } 

   // sayhi():void{
   // console.log("hi all");
   // }

}

let e1 = new Employee("Sachin","Tendulkar");
e1.disp();
 