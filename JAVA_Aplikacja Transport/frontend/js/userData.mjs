const emailInput = document.getElementById('staticEmail2');
const userNameInput = document.getElementById('staticUsername');
const userPassword = document.getElementById('staticPassword');

const formEmail = document.getElementById('formEmail');
const formUsername = document.getElementById('formUsername');
const formPassword = document.getElementById('formPassword');

const usernameInputNew = document.getElementById("username");
const emailInputNew = document.getElementById("email");
const passwordInputNew = document.getElementById("password");

// Wczytanie danych użytkownika z serwera za pomocą biblioteki Axios
axios.get('http://localhost:8081/users/{id}')
  .then(response => {
    const user = response.data;
    emailInput.value = user.email;
    userNameInput.value = user.username;
    userPassword.value = user.password;
  })
  .catch(error => {
    console.error(error);
  });

  
function toggleFormEmail() {
  if (formEmail.style.display === "none") {
      formEmail.style.display = "block";
  } else {
      formEmail.style.display = "none";
  }
}

function toggleFormUsername() {
  if (formUsername.style.display === "none") {
      formUsername.style.display = "block";
  } else {
      formUsername.style.display = "none";
  }
}

function toggleFormPassword() {
  if (formPassword.style.display === "none") {
      formPassword.style.display = "block";
  } else {
      formPassword.style.display = "none";
  }
}


formEmail.addEventListener("submitEmail", (event) => {
  const email = emailInputNew.value;
  event.preventDefault();
  axios
    .patch("http://localhost:8081/user/{id}", {
      email: email
    })
});

formUsername.addEventListener("submitUsername", (event) => {
  const username = usernameInputNew.value;
  event.preventDefault();
  axios
    .patch("http://localhost:8081/user/{id}", {
      username: username
    })
});

formPassword.addEventListener("submitPassword", (event) => {
  const password = passwordInputNew.value;
  event.preventDefault();
  axios
    .patch("http://localhost:8081/user/{id}", {
      password: password
    })
});

