package io.github.jonaskahn.controllers.patientrequest

import java.time.Instant
import java.time.LocalDate

class PatientRequestForm {
     var numberOrder: Int? = null
     var patientNumber: String? = null
     var medicineCode: String? = null
     var patientName: String? = null
     var department: String? = null
     var saveNumber: Int? = null
     var inDate: LocalDate? = null
     var outDate: LocalDate? = null
     var receptionDate: Instant? = null
     var appointmentPatientDate: LocalDate? = null
     var copyQuantity: Int? = null
     var copyCost: Int? = null
     var note: String? = null
}