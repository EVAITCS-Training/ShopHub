interface Animal {
    name: string;
    age: number;
    speak(): void;
}


const dog: Animal = {
    name: "Buddy",
    age: 3,
    speak() {
        console.log("Woof! Woof!");
    }
}