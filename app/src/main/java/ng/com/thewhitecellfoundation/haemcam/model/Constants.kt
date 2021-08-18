package ng.com.thewhitecellfoundation.haemcam.model

var doctorAppointment: ArrayList<Appointment> = arrayListOf(
    Appointment("Medical Check Up", "Doctor Darot"),
    Appointment("Eye Test", "Doctor Dapo"),
    Appointment("Back Pain", "Doctor Ola")
)
var doctorAppointment2: ArrayList<Appointment> = arrayListOf(
    Appointment("Medical Check Up", "Doctor Darot"),
    Appointment("Eye Test", "Doctor Dapo"),

)
var doctorAppointment3: ArrayList<Appointment> = arrayListOf(
    Appointment("Medical Check Up", "Doctor Darot"),

)

var listOfNotificationData = listOf(
    NotificationData("12th of May 2020", doctorAppointment),
    NotificationData("14th of May 2020", doctorAppointment2),
    NotificationData("16th of May 2020", doctorAppointment3),

)
