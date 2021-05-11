package ng.com.thewhitecellfoundation.haemcam.model

class Regimen(var name: String, var drugList: List<RegimenDrug>, var cycleDays: Long, var id: Long = 0) {

    companion object {
        var staticId = 0.toLong()
    }
    init {
        id = ++staticId
    }
}

class RegimenDrug(var name: String, daysInCircle: List<Long>)
