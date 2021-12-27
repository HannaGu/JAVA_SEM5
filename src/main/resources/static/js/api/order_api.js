let token=localStorage.getItem("token");
let curUrl =  document.URL.split('/')
let tutor_id = curUrl[4]


async function onOrderLoad(){
   await getTutorById(tutor_id, token).then(data=> {
        tutorInfo.innerHTML = `Репетитор: ${data.name} ${data.surname} <br> 
                Email: ${data.email} <br> 
                Предмет: ${data.subject} <br>
                Oплата в час: `;
        costPerHour.innerHTML=`${data.cost}`;
         })
}
function totalCost(){
    let costPerHour =parseInt(document.getElementById("costPerHour").innerHTML);
    let orderHours = document.getElementById("orderHours").value;
    let total = costPerHour*orderHours;
    return total;
}


async function createContract(data) {
    if(confirm(`Итоговая сумма: ${totalCost()}. Заказать?`)){
        fetch('/user/createContract',{
            method:'POST',
            headers:{'Authorization': `Bearer ${token}`,
                    'Content-Type':'application/json'},
            body:JSON.stringify({
                tutor_id:tutor_id,
                orderHours:document.getElementById("orderHours").value,
                totalCost:totalCost()
            })
        }).then(result=>{
            if(result.ok){
                alert("Ваша заявка успешно оформлена");
                window.location.replace(window.location.origin);
            }
        })
    }
}
