<template>
    <div class="wrappper">

        <nav class="navbar">
            <div class="logo">Calculating</div>
            <ul class="nav-links">
                <input type="checkbox" id="checkbox_toggle"/>
                <label for="checkbox_toggle" class="hamburger">&#9776;</label>
                
                <div class="menu">
                    <li><a href="/">Home</a></li>
                    <li><a @click="changeRoute('Calculator')">Calculator</a></li>
                    <li><a @click="changeRoute('Contact')">Contact</a></li>
                </div>
            </ul>
        </nav>
        <div class="inner">
            <form action>
                <h3>Contact us</h3>
                <p>If you have some feedback for us, please leave it here!</p>
                <label class="form-group">
                    <input type="text" class="form-control" v-model="feedback.name" @input="updateName" required>
                    <span>Your name</span>
                    <span class="border"></span>
                </label>
                <label class="form-group">
                    <input type="text" class="form-control" v-model="feedback.email" @input="validateEmail" required>
                    <span for>Your mail</span>
                    <span class="border"></span>
                </label>
                <label class="form-group">
                    <textarea name id class="form-control" v-model="feedback.message" required></textarea>
                    <span for>Your Message</span>
                    <span class="border"></span>
                </label>
                <button type="submit" :disabled="!isEmailValid" @click.prevent="submitForm()" :class="{ 'disabled': hasEmptyFields }">Submit</button>

                <h5>{{ submitResult }}</h5>
            </form>
            <div v-if="loading" class="loading-bar">
      </div>
        </div>
        
      </div>
      <h1>Reviews</h1>
    </template>


<script scoped>
import axios from 'axios';
export default {
  data() {
    return {
      feedback: {
        name: '',
        email: '',
        message: ''
      },
      submitResult: "",
      feedbacks: [],
      hasEmptyFields: true // new property
    }
  },
  watch: {
  feedback: {
    handler() {
      this.hasEmptyFields = Object.values(this.feedback).some(field => !field)
    },
    deep: true
  }
},

  computed: {
    isEmailValid() {
      const emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return emailRegex.test(this.feedback.email);
    }
  },
  methods: {

    async submitForm() {
      this.$store.commit("SET_NAME", this.feedback.name);
      this.$store.commit("SET_EMAIL", this.feedback.email);
      await axios.post("http://localhost:3000/feedbacks", this.feedback)
      .then(response => {
        setTimeout(() => {
          console.log(response.data)
          this.submitResult = "Feedback has been sent!"
          this.feedback.name = this.$store.state.name;
          this.feedback.email = this.$store.state.email;
          this.feedback.message = "";
        },1000);
      })
      .catch(error => {
        setTimeout(() => {
          console.log(error.response.data)
          this.submitResult = "There has occured an error."
        },1000);
      })
    
    },
    updateName(event) {
      this.$store.commit("SET_NAME", event.target.value);
    },
    updateEmail(event) {
      this.$store.commit("SET_EMAIL", event.target.value);
    },
    changeRoute(string) {
        this.$router.push({name:string})
    },
  },
  created() {
    this.feedback.name = this.$store.state.name;
    this.feedback.email = this.$store.state.email;
  }
}
</script>

<style scoped>

#feedbackContainer {
  display: flex;
  flex-direction: column;
  gap: 50px;
  margin-top: 15px;
}

#feedbackDiv {
  padding: 10px;
  box-shadow: 7px 7px 4px;
  background-color: rgb(222, 240, 255);
}

.loading-bar {
    width: 100%;
    height: 10px;
    background-color: lightgray;
    position: relative;
    top: 10px;
  }
  
  .loading-bar-progress {
    height: 100%;
    background-color: rgb(0, 255, 55);
    transition: width 0.4s linear;
  }

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box; }
body {
  font-family: "Montserrat-Regular";
  color: #fff;
  font-size: 15px;
  background: url("../assets/contactGround.png") no-repeat;
  margin: 0; }

input, textarea, select, button {
  font-family: "Montserrat-Regular";
  color: #fff;
  font-size: 15px; }

p, h1, h2, h3, h4, h5, h6, ul {
  margin: 0; }

img {
  max-width: 100%; }

ul {
  padding-left: 0;
  margin-bottom: 0; }

a:hover {
  text-decoration: none; }

:focus {
  outline: none; }

.wrapper {
  min-height: 100vh;
  display: flex;
  align-items: center;
  background-size: cover;
}

.inner {
  max-width: 758px;
  margin: auto;
  background: teal;
  border: 10px solid rgb(0, 88, 88);
  padding: 77px 99px 87px;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
  -webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
  -ms-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
  -o-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
  }

textarea {
  resize: none; }

h3 {
  text-transform: uppercase;
  font-size: 45px;
  font-family: "Montserrat-Bold";
  text-align: center;
  margin-bottom: 12px;
  color: white; }

p {
  text-align: center;
  padding: 0 10px;
  margin-bottom: 55px;
  line-height: 1.8;
  color: white }

  h5 {
  text-align: center;
  padding: 0 10px;
  margin-bottom: 55px;
  line-height: 1.8;
  color: white }

.form-group {
  position: relative;
  display: block;
  margin-bottom: 48px; }
  .form-group span {
    font-size: 15px;
    color: rgb(250, 250, 250);
    position: absolute;
    top: 11px;
    transition: all .2s ease;
    transform-origin: 0 0;
    cursor: text; }
  .form-group span.border {
    height: 2px;
    display: block;
    position: absolute;
    width: 100%;
    left: 0;
    top: 41px;
    transform: scaleX(0);
    transition: all .15s ease;
    background: #fff; }

.form-control {
  border: none;
  border-bottom: 2px solid rgb(0, 88, 88);;
  display: block;
  width: 100%;
  height: 43px;
  font-size: 15px;
  background: none;
  font-family: "Montserrat-SemiBold"; }
  .form-control:focus, .form-control:valid {
    border-bottom: 2px solid #fff; }
    .form-control:focus + span, .form-control:valid + span {
      transform: translateY(-22px) scale(0.8); }
      .form-control:focus + span + .border, .form-control:valid + span + .border {
        transform: scaleX(1); }

textarea.form-control {
  padding-top: 10px;
  padding-bottom: 10px; }

button {
  border: none;
  width: 162px;
  height: 51px;
  border: 2px solid #fff;
  margin: auto;
  margin-top: 60px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
  background: none;
  color: #fff;
  text-transform: uppercase;
  font-family: "Montserrat-SemiBold";
  -webkit-transform: perspective(1px) translateZ(0);
  transform: perspective(1px) translateZ(0);
  position: relative;
  -webkit-transition-property: color;
  transition-property: color;
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  cursor: pointer;
  webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -khtml-user-select: none; /* Konqueror HTML */
  -moz-user-select: none; /* Old versions of Firefox */
  -ms-user-select: none; 
  -user-select: none;  }
  button i {
    margin-left: 10px;
    -webkit-transform: translateZ(0);
    transform: translateZ(0);
    -webkit-transition-duration: 0.1s;
    transition-duration: 0.1s;
    -webkit-transition-property: transform;
    transition-property: transform;
    -webkit-transition-timing-function: ease-out;
    transition-timing-function: ease-out; }
  button:before {
    content: "";
    position: absolute;
    z-index: -1;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: #2098D1;
    -webkit-transform: scaleX(0);
    transform: scaleX(0);
    -webkit-transform-origin: 0 50%;
    transform-origin: 0 50%;
    -webkit-transition-property: transform;
    transition-property: transform;
    -webkit-transition-duration: 0.3s;
    transition-duration: 0.3s;
    -webkit-transition-timing-function: ease-out;
    transition-timing-function: ease-out; }
  button:hover {
    border-color: transparent; }
    button:hover:before {
      -webkit-transform: scaleX(1);
      transform: scaleX(1); }
    button:hover i {
      -webkit-transform: translateX(4px);
      transform: translateX(4px); }
  button.disabled {
  background-color: grey;
  color: white;
  cursor: not-allowed;
}


@media (max-width: 767px) {
  h3 {
    font-size: 38px; }

  p {
    font-size: 14px;
    padding: 0; }

  .inner {
    padding: 27px 20px 37px;
    border: none;
    box-shadow: none;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    -ms-box-shadow: none;
    -o-box-shadow: none; }

  .wrapper {
    background: teal;
    border: 10px solid #0d99d7; } }

div {
    display: block;
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
margin-bottom: 30px;
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