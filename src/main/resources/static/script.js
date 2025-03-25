function updateColor() {
        
    const selectElement = document.getElementById('state');
    

    const selectedValue = selectElement.value;
    
   
    selectElement.classList.remove('to-do', 'in-progress', 'completed');
    
  
    if (selectedValue === 'TO_DO') {
        selectElement.classList.add('to-do')
    } else if (selectedValue === 'IN_PROGRESS') {
        selectElement.classList.add('in-progress')
    } else if (selectedValue === 'COMPLETED') {
        selectElement.classList.add('completed')
    }


updateColor();


selectElement.addEventListener('change', updateColor);
}
const form = document.getElementById('ticket-create-form');
form.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    fetch('/ticket/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    }).then(response => {
        if (response.ok) {
            window.location.href = '/ticket';
        } else {
            alert('Errore durante la creazione del ticket');
        }
    });
});