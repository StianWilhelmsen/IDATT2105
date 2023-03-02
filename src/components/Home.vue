<template>
  <body class="background">
        <nav class="navbar">
      <div class="logo">Calculating</div>
      <ul class="nav-links">
        <input type="checkbox" id="checkbox_toggle"/>
        <label for="checkbox_toggle" class="hamburger">&#9776;</label>

        <div class="menu">
          <li><a href="/">Home</a></li>
          <li><a href="#" @click="changeRoute('Calculator')">Calculator</a></li>
          <li><a @click="changeRoute('Contact')">Contact</a></li>
        </div>
      </ul>
    </nav>

    <div class="content">
      <div class="textContent">
        <h2>Welcome to Calculating v3.0</h2>
        <p>On this page you are able to use a calculator<br>
        or you can give us feedback on how the calculator works.<br><br>
        Press the buttons to the right to navigate</p>
      </div>
      <div class="buttons">
        <div class="card">
          <div class="card2" @click="changeRoute('Calculator')">
            <p>To the Calculator</p>
         </div>
        </div>
        <div class="pictures">

        </div>
      </div>
    </div>

  </body>

</template>

<script scoped>


export default {
  name: 'HomePage',
  methods: {
    changeRoute(string) {
        this.$router.push({name:string})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.background {
}

.content{
  margin-top: 30px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  align-items: center;
  text-align: center;
}

.card {
  width: 190px;
  height: 120px;
  background-image: linear-gradient(163deg, #11dd70 0%, #3508d8 100%);
  border-radius: 20px;
  transition: all 0.2s;
  margin: auto;
}

.card2 {
  width: 190px;
  height: 120px;
  background-color: #3b3636;
  transition: all 0.2s;
  border-radius: 20px;
  cursor: pointer;
}

.card2:hover {
  transform: scale(0.98);
  border-radius: 20px;
}

.card:hover {
  box-shadow: 0px 0px 40px 2px rgba(6, 204, 98, 0.3);
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
