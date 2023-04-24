const registerForm = document.querySelector("#register-form");
const registerMessage = document.querySelector("#register-message");

const usernameInput = document.getElementById("username");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");

registerForm.addEventListener("submit", (event) => {
  const username = usernameInput.value;
  const email = emailInput.value;
  const password = passwordInput.value;
  event.preventDefault();
  const formData = new FormData(registerForm);
  if (formData.get("password") !== formData.get("confirm-password")) {
    registerMessage.textContent = "Passwords do not match";
    return;
  }
  axios
    .post("http://localhost:8081/api/auth/signup", {
      username: username,
      email: email,
      password: password,
    })
    .then((response) => {
      console.log(response.data);
      registerMessage.textContent = "Registration successful";
    })
    .catch((error) => {
      console.log(error);
      registerMessage.textContent = "Registration failed";
    });
});
