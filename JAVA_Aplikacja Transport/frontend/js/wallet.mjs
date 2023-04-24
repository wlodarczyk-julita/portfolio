const portfelLink = document.getElementById('portfel-link');
  const koszykLink = document.getElementById('koszyk-link');
  const transakcjeLink = document.getElementById('transakcje-link');

  const portfelContent = document.getElementById('portfel-content');
  const koszykContent = document.getElementById('koszyk-content');
  const transakcjeContent = document.getElementById('transakcje-content');

  portfelLink.addEventListener('click', () => {
    portfelLink.classList.add('active');
    koszykLink.classList.remove('active');
    transakcjeLink.classList.remove('active');

    portfelContent.style.display = 'block';
    koszykContent.style.display = 'none';
    transakcjeContent.style.display = 'none';
  });

  koszykLink.addEventListener('click', () => {
    portfelLink.classList.remove('active');
    koszykLink.classList.add('active');
    transakcjeLink.classList.remove('active');

    portfelContent.style.display = 'none';
    koszykContent.style.display = 'block';
    transakcjeContent.style.display = 'none';
  });

  transakcjeLink.addEventListener('click', () => {
    portfelLink.classList.remove('active');
    koszykLink.classList.remove('active');
    transakcjeLink.classList.add('active');

    portfelContent.style.display = 'none';
    koszykContent.style.display = 'none';
    transakcjeContent.style.display = 'block'; })

    // pobieramy referencję do elementu input
const kwotaInput = document.querySelector('input.form-control');

// ustawiamy wartość początkową input na 0
kwotaInput.value = '0';

// pobieramy referencję do elementu wyświetlającego wartość portfela
const portfelWartosc = document.querySelector('p.card-text');

// ustawiamy początkową wartość portfela na 0
let portfel = 0;

// pobieramy referencję do przycisku "Wpłać"
const wplacBtn = document.querySelector('a.btn');

// dodajemy obsługę zdarzenia dla przycisku "Wpłać"
wplacBtn.addEventListener('click', function() {
  // pobieramy wartość z pola input
  const kwota = parseFloat(kwotaInput.value);
  
  // dodajemy kwotę do wartości portfela
  portfel += kwota;
  
  // wyświetlamy aktualną wartość portfela
  portfelWartosc.textContent = `${portfel} PLN`;
  
  // resetujemy wartość pola input na 0
  kwotaInput.value = '0';
});