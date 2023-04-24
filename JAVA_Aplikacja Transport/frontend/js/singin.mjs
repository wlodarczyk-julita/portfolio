const loginForm = document.querySelector("#login-form");
const loginMessage = document.querySelector("#login-message");
const CONNECTION_URL = 'http://localhost:8089/connections';

const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");

loginForm.addEventListener("submit", (event) => {
    const username = usernameInput.value;
  const password = passwordInput.value;
  event.preventDefault();
  const formData = new FormData(loginForm);
  axios
    .post("http://localhost:8081/api/auth/signin", {
    username: username,
    password: password,
    })
    .then((response) => {
      console.log(response.data);
      window.open(CONNECTION_URL, "_blank");
      loginMessage.textContent = "Logowanie przebiegło pomyślnie";
    })
    .catch((error) => {
      console.log(error);
      loginMessage.textContent = "Logowanie nie powiodło się";
    });
});
