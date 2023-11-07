function updateDateTime() {
    var now = new Date();  // Get current date and time
    var formattedDateTime = now.toLocaleString();  // Format date and time
    document.getElementById('dateTime').textContent = formattedDateTime;  // Set date and time in HTML
}

document.getElementById('clock_in').addEventListener('click',function(){
    alert('出勤しました');
});

document.getElementById('clock_out').addEventListener('click',function(){
    alert('退勤しました');
});

//init
updateDateTime();
setInterval(updateDateTime, 1000);