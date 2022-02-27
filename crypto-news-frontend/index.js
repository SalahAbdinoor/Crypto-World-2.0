$(window).on("hashchange", function () {
  if (location.hash.slice(1) == "signup") {
    document.getElementById("errorMsg").innerHTML = ""

    $(".page").addClass("extend")
    $("#login").removeClass("active")
    $("#signup").addClass("active")
  } else {
    document.getElementById("errorMsg").innerHTML = ""

    $(".page").removeClass("extend")
    $("#login").addClass("active")
    $("#signup").removeClass("active")
  }
})
$(window).trigger("hashchange")
$(".login").submit(function(e) {
  e.preventDefault();
});

$(window).trigger("hashchange")
$(".signup").submit(function(e) {
  e.preventDefault();
});
$(window).trigger("hashchange")
$(".hello").submit(function(e) {
  e.preventDefault();
});

$( ".target" ).change(function() {
  alert( "Handler for .change() called." );
});

//------------------- Start: Login -------------------//

async function validateLoginForm() {
 
  var username = document.getElementById("logName").value
  var password = document.getElementById("logPassword").value

const url = "http://localhost:8080/authenticate"

var data = {
  "username": username,
  "password": password
}

if (username === "" || password === "") {
  document.getElementById("errorMsg").innerHTML =
    "Please fill the required fields"
    document.getElementById("errorMsg").style.color = "red";

  return false
} else if (password.length < 4) {
  document.getElementById("errorMsg").innerHTML =
    "Your password must include atleast 4 characters"
    document.getElementById("errorMsg").style.color = "red";

  return false
} else {
  try {
  
    const fetchToken = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
          
        },
        body: JSON.stringify(data),
      })

      const authToken = await fetchToken.json()
            
      loginResource(authToken)
  
    } catch (error) {
      document.getElementById("errorMsg").innerHTML =
      "Wrong username & password!"
      console.error("error", error);
    }
  return true
  }   
}

//------------------

async function loginResource(jwt){
  const url = "http://localhost:8080/user/get"

  var { jwt } = jwt;

  localStorage.setItem('jwt', jwt);

 const test = await fetch(url, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${jwt}`,
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
  })
.then(response => {
  return response.json()})
.then(data => {
  return data})

  console.log("Test", test);

  $(".page").addClass("hello")

  const header = document.getElementById("header")
  header.innerHTML = ""
  // Build card

  var welcome = `
    <h1> Welcome ${test[0]} </h1>
    </br>
    <p> Your token expires at: \n ${test[1]}</p>
    `

    document.getElementById("helloTitle").innerHTML = welcome;

} 

//------------------- Stop: Login -------------------//

//------------------- Start: Sign Up -------------------//

async function validateSignupForm() {
  var mail = document.getElementById("signEmail").value
  var name = document.getElementById("signName").value
  var password = document.getElementById("signPassword").value

  if (mail == "" || name == "" || password == "") {
    document.getElementById("errorMsg").innerHTML =
      "Please fill the required fields"
      document.getElementById("errorMsg").style.color = "red";
    return false
  } else if (password.length < 8) {
    document.getElementById("errorMsg").innerHTML =
      "Your password must include atleast 8 characters"
      document.getElementById("errorMsg").style.color = "red";

    return false
  } else {
    return await signUpResource(mail, name, password);
  }
}

//------------------

async function signUpResource(mail, name, password){

  const data = {
    mail: mail,
    name: name,
    password: password
  }

  const url = "http://localhost:8080/user/register"

  const jwt = localStorage.getItem('jwt');

  const signUp = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    body: JSON.stringify(data),
  })
  .then(response => {
    return response.json()})
  .then(data => {
    return data})

    if (signUp) {
      
      document.getElementById("errorMsg").innerHTML = signUp
      document.getElementById("errorMsg").style.color = "greenyellow";

    }

    
  return false
}
//------------------- Stop: Sign Up -------------------//
//------------------- Start: Sign Out -------------------//

function signOutForm(){

  $(".page").removeClass("hello")

  const headerData = `  <a id="login" class="active" href="#login">login</a>
  <a id="signup" href="#signup">signup</a>`

  const header = document.getElementById("header")
  header.innerHTML =  headerData


}
//------------------- Start: Sign Out -------------------//
