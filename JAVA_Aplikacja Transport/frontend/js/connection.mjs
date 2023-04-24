const FormFirstStation = document.querySelector("#form-group-first-station");

const firstStationSelect = document.getElementById('first-station-select');
const finalStationSelect = document.getElementById('final-station-select');
const startDateSelect = document.getElementById('start-date-select');
const finalDateSelect = document.getElementById('final-date-select');
const finalPriceSelect = document.getElementById('price-select');
const trainNameSelect = document.getElementById('trainName-select');
const seatsSelectValue = document.getElementById('seats-select-1');
const seatsSelectValue2 = document.getElementById('seats-select-2');
const seatsSelectValue3 = document.getElementById('seats-select-3');
const seatsSelectValue4 = document.getElementById('seats-select-4');
const seatsSelectValue5 = document.getElementById('seats-select-5');
const seatsSelectValue6 = document.getElementById('seats-select-6');
const seatsSelectValue7 = document.getElementById('seats-select-7');
const seatsSelectValue8 = document.getElementById('seats-select-8');
const seatsSelectValue9 = document.getElementById('seats-select-9');
const seatsSelectValue10 = document.getElementById('seats-select-10');
const finalValueTicketSelect = document.getElementById('numer-tickets-select');
const seatsSelects = document.querySelectorAll('.form-group-seats select');
console.log(firstStationSelect);


axios.get('http://localhost:8089/connections')
.then(response => {
  const connections = response.data;

  // Grupowanie połączeń wg stacji początkowych
  const stations = connections.reduce((acc, curr) => {
    const { startStation } = curr;
    if (!acc.includes(startStation)) {
      acc.push(startStation);
    }
    return acc;
  }, []);

  // Utwórz listę rozwijaną z pobranych danych
  stations.forEach(station => {
    const option = document.createElement('option');
    option.value = station;
    option.textContent = station;
    firstStationSelect.appendChild(option);
  });
})
.catch(error => console.error(error));

firstStationSelect.addEventListener('change', function() {
  const startStation = this.value;
  axios.get('http://localhost:8089/connections/start?startStation=' + startStation)
  .then(response => {
      const stacjeKoncowe = response.data;
      finalStationSelect.innerHTML = ''; // wyczyść listę stacji końcowych
      stacjeKoncowe.forEach(stacja => {
          const option = document.createElement('option');
          option.value = stacja.id;
          option.textContent = stacja.finalStation;
          finalStationSelect.appendChild(option);
      });
  })
  .catch(error => console.error(error));
});


  
finalStationSelect.addEventListener('change', function() {
  const finalStation = this.value;
  finalPriceSelect.innerHTML = '';

  console.log(finalStation)
  axios.get(`http://localhost:8089/connections/${finalStation}`)
  .then(response => {
  const chooseConnection = response.data;
  //console.log(priceList.price)
  // priceList.forEach(price => {
    const optionPrice = document.createElement('option');
    optionPrice.textContent = chooseConnection.price;
    const intValueOption = parseInt(optionPrice.textContent);
    const intValueTicket = parseInt(finalValueTicketSelect.value)
    optionPrice.textContent = intValueOption * parseInt(finalValueTicketSelect.value);
    finalPriceSelect.appendChild(optionPrice);

    const optionTrainName = document.createElement('option');
    optionTrainName.textContent = chooseConnection.trainName;
    trainNameSelect.appendChild(optionTrainName);

    const optionStartDate = document.createElement('option');
    optionStartDate.textContent = chooseConnection.deparature;
    startDateSelect.appendChild(optionStartDate);

    const optionFinalDate = document.createElement('option');
    optionFinalDate.textContent = chooseConnection.arrive;
    finalDateSelect.appendChild(optionFinalDate);


    const seatsSelectValues = [seatsSelectValue, seatsSelectValue2, seatsSelectValue3,
      seatsSelectValue4, seatsSelectValue5, seatsSelectValue6,
      seatsSelectValue7, seatsSelectValue8, seatsSelectValue9, seatsSelectValue10];
    const seats = chooseConnection.capacity;
    for (let j = 1; j <= parseInt(seats); j++) {
      const optionSeats = document.createElement('option');
      optionSeats.textContent = j;
      seatsSelectValues.forEach(select => {
        select.appendChild(optionSeats.cloneNode(true));
      });
    }

// });
})
  .catch(error => console.error(error));
});

// ukryj elementy z klasą "hidden"
seatsSelects.forEach((select) => {
  select.style.display = 'none';
});
  
finalValueTicketSelect.addEventListener('change', function() {
  const selectedOption = this.value;
  // pokaż tyle elementów z klasą "hidden", ile jest wybranych biletów
  for (let i = 0; i < selectedOption; i++) {
    seatsSelects[i].style.display = 'inline-block';
    seatsSelects[i].classList.remove('hidden');
  }
  // ukryj pozostałe elementy z klasą "hidden"
  for (let i = selectedOption; i < seatsSelects.length; i++) {
    seatsSelects[i].style.display = 'none';
    seatsSelects[i].classList.add('hidden');
  }
});


const form = document.querySelector('#my-form');
form.addEventListener('submit', (event) => {
const currentDate = new Date();

  // const formData = new FormData(form); // Tworzy obiekt FormData z danymi z formularza
  axios.post('http://localhost:8082',  {
    status: false,
    bookingDate: currentDate,
  })
    .then(response => {
      console.log(response.data);
      alert('Pomyślnie dodano połączenie!');
      form.reset(); // Czyści formularz
    })
    .catch(error => {
      console.error(error);
      alert('Wystąpił błąd podczas dodawania połączenia!');
    });
});
