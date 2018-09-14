package com.primesoft.solarcalculator.controller

fun calculateUnitsCoverdBySolarPower
        (currentUnits: Int, coveringPercentageFromSolar: Int) = (currentUnits * coveringPercentageFromSolar)/ 100


fun calculatePanelsNeeded(noOfUnitsCoveredBySolar: Int) = Math.ceil((noOfUnitsCoveredBySolar*1000) / 320 / 4.2 / 30)

fun calculateNoofInvertersNeeded(noOfpanels: Double) = Math.ceil(noOfpanels / 2)