<template>
    <nav class="navbar">
        <div class="logo">Calculating</div>
            <ul class="nav-links">
            <input type="checkbox" id="checkbox_toggle"/>
            <label for="checkbox_toggle" class="hamburger">&#9776;</label>

        <div class="menu">
            <li><a @click="changeRoute('Home')">Home</a></li>
            <li><a @click="changeRoute('Calculator')">Calculator</a></li>
            <li><a @click="changeRoute('Contact')">Contact</a></li>
        </div>
        </ul>
    </nav>

    <div class="container">
        <div class="calculator">
            <div class="result">{{ current || 0 }}</div>
                <div class="buttons">
                    
                    <div @click="clear()" id="optionBtn" class="calcBtn">C</div>
                    <div @click="sign()" id="optionBtn" class="calcBtn">+/-</div>
                    <div @click="delfunc()" id="optionBtn" class="calcBtn">DEL</div>
                    <div @click="add" id="operatorBtn" class="calcBtn">+</div>
                    <div @click="append(1)" class="calcBtn">1</div>
                    <div @click="append(2)" class="calcBtn">2</div>
                    <div @click="append(3)" class="calcBtn">3</div>
                    <div @click="subtract()" id="operatorBtn" class="calcBtn">-</div>
                    <div @click="append(4)" class="calcBtn">4</div>
                    <div @click="append(5)" class="calcBtn">5</div>
                    <div @click="append(6)" class="calcBtn">6</div>
                    <div @click="multiply()" id="operatorBtn" class="calcBtn">X</div>
                    <div @click="append(7)" class="calcBtn">7</div>
                    <div @click="append(8)" class="calcBtn">8</div>
                    <div @click="append(9)" class="calcBtn">9</div>
                    <div @click="divide()" id="operatorBtn" class="calcBtn">/</div>
                    <div @click="percentage()" class="calcBtn">%</div>
                <div @click="append(0)" class="calcBtn">0</div>
                <div @click="append('.')" class="calcBtn">.</div>
                <div @click="equals()" id="equals" class="calcBtn">=</div>
            </div>
          </div>
          <div class="logg">
            <ul>
              <li v-for="(message) in equations" :key="message">
                {{ message || ""}}
              </li>
            </ul>
          </div>
          <div id="testBackend">
            <button @click="kaEgVil">Press me bitch</button>
            <p class="backendString">{{ testString }}</p>
          </div>
              
    </div>
</template>

<script scoped>
import axios from 'axios';
export default {
    name: 'CalculatorPage',
    data() {
    return {
      testString:  "",
      current: "",
      operator: null,
      previous: null,
      operatorClicked: true,
      equations: [
        
      ]
    }
  },

  methods: {

    async sendCalculation() {
      try {
        const equation = {
          a: this.previous,
          b: this.current,
          operator: this.operator
        }
        console.log(equation);
        this.current = await(await(axios.post("http://localhost:8080/Calculator", equation))).data;
      } catch (error) {
        console.error(error);
      }
    },
    
    async kaEgVil() {
      this.testString = await(await(axios.get("http://localhost:8080/Calculator").catch(error => {
        console.log(error)
      }))).data
    },
    clear() {
      this.current = '';
      this.previous = null;
    },
    append(number) {
      if (this.operatorClicked) {
        this.current = '';
        this.operatorClicked = false;
      }
      if (number === '.') {
        if (!this.current.includes('.')) {
          this.current = `${this.current}${number}`;
        }
      } else {
        this.current = `${this.current}${number}`;
      }
    },
    sign() {
      this.current = this.current.charAt(0) === '-' ?
      this.current.slice(1): `-${this.current}`;
    },
    add() {
      this.operator = "+"
      this.setPrevious();
    },
    subtract() {
      this.operator = "-"
      this.setPrevious();
    },
    divide() {
      this.operator = "/"
      this.setPrevious();
    },
    multiply() {
      this.operator = "*"
      this.setPrevious();
    },
    setPrevious() {
      this.previous = this.current;
      this.operatorClicked = true;
      this.current = "";
    },

    equals() {
  if (this.previous == null) {
    alert('Not a valid expression');
  } else {
    this.sendCalculation();
  }
},

    percentage() {
      this.current = this.current / 100;
    },
    delfunc() {
      this.current = this.current.slice(0, this.current.length - 1);
    },
    changeRoute(string) {
        this.$router.push({name:string})
    }
  }
}
</script>

<style scoped>
.container {
    margin: 150px;
    padding: 20px;
    display: flex;
    gap: 30px;
    justify-content: center;
}

.logg {
    width: 300px;
    border: groove;
    display: flex;
    flex-direction: column;
    background-color: aliceblue;
    box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.1); /* add box-shadow */
    transform: translateZ(10px); /* add transform */
    transform-style: preserve-3d; /* add transform-style */
    perspective: 1000px; /* add perspective */
}

.result {
    height: 60px;
    margin: 10px;
    color: white;
    border-radius: 5px;
    text-align: right;
    font-family: 'Calculator', sans-serif;
    font-size: 30px;
    padding: 10px;
    background-color: rgb(88, 88, 88);
    border: 1px rgba(255, 255, 255, 0.63) solid;
}

.calculator {
    border: groove;
    display: flex;
    flex-direction: column;
    width: fit-content;
    height: fit-content;
    background-color: rgb(49, 49, 49);
}

.buttons {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    margin: 5px;
    padding: 5px 10px;
    cursor: pointer;
    webkit-touch-callout: none; /* iOS Safari */
    -webkit-user-select: none; /* Safari */
     -khtml-user-select: none; /* Konqueror HTML */
       -moz-user-select: none; /* Old versions of Firefox */
        -ms-user-select: none; 
            user-select: none; 
}

.calcBtn {
    width: 60px;
    height: 60px;
    background-color: rgb(223,222,222);
    border-radius: 5px;
    border:groove;
    display: grid;
    place-items: center;
    font-family: 'Calculator';
    font-size: 30px;
}

#optionBtn {
    background-color: rgb(167, 165, 165);
}

#operatorBtn {
    background-color: orange
}

#equals {
    background-color: rgb(255, 166, 0);
}  

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}


a {
  text-decoration: none;
}

li {
  list-style: none;
}

.navbar {
display: flex;
align-items: center;
justify-content: space-between;
padding: 20px;
background-color: teal;
color: #fff;
}

.nav-links a {
  color: #fff;
}

.logo {
  font-size: 32px;
}

.menu {
  display: flex;
  gap: 1em;
  font-size: 18px;
}

.menu li:hover {
  background-color: #4c9e9e;
  border-radius: 5px;
  transition: 0.3s ease;
}

.menu li {
  padding: 5px 14px;
}

input[type=checkbox] {
  display: none;
}

.hamburger {
  display: none;
  font-size: 24px;
  user-select: none;
}

@media(max-width: 768px) {
  .menu {
    display: none;
    position: absolute;
    background-color: teal;
    right: 0;
    left: 0;
    text-align: center;
    padding: 16px 0;
  }
  .menu li:hover {
    display: inline-block;
    background-color: #4c9e9e;
    transition: 0.3s ease;
  }

  .menu li + li {
    margin-top: 12px;
  }

  input[type=checkbox]:checked ~ .menu {
    display: block;
  }

  .hamburger {
    display: block;
  }
}
</style>