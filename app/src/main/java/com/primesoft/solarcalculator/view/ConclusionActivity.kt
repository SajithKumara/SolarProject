package com.primesoft.solarcalculator.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.primesoft.solarcalculator.R
import com.primesoft.solarcalculator.common.Constant.Companion.CEB_CHARGES
import com.primesoft.solarcalculator.common.Constant.Companion.COUNDUTES_NAILS_SWITCHGEARS
import com.primesoft.solarcalculator.common.Constant.Companion.CURRENT_UNITS
import com.primesoft.solarcalculator.common.Constant.Companion.EARTH_WIRE
import com.primesoft.solarcalculator.common.Constant.Companion.MAIN_WIRE_1M
import com.primesoft.solarcalculator.common.Constant.Companion.PANEL_320W
import com.primesoft.solarcalculator.common.Constant.Companion.PERCENTAGE_COVERED
import com.primesoft.solarcalculator.common.Constant.Companion.SERVICE_CHARGE_PER_PANEL
import com.primesoft.solarcalculator.common.Constant.Companion.SOLAR_INVERTER
import com.primesoft.solarcalculator.common.Constant.Companion.SURGE_ARRESTER
import com.primesoft.solarcalculator.common.Constant.Companion.TRANSPORT
import com.primesoft.solarcalculator.common.Constant.Companion.TWO_POLE_BREAKER
import com.primesoft.solarcalculator.common.Constant.Companion.UNIT_ONE
import com.primesoft.solarcalculator.common.Constant.Companion.WATT_METER
import com.primesoft.solarcalculator.common.readInt
import com.primesoft.solarcalculator.controller.calculateNoofInvertersNeeded
import com.primesoft.solarcalculator.controller.calculatePanelsNeeded
import com.primesoft.solarcalculator.controller.calculateUnitsCoverdBySolarPower
import kotlinx.android.synthetic.main.activity_conclusion.*

class ConclusionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conclusion)
        supportActionBar?.title = "Your plan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val currentUnits = readInt(CURRENT_UNITS)
        val percentage = readInt(PERCENTAGE_COVERED)
        if ( currentUnits != 0 && percentage != 0){
            val averageUnits = calculateUnitsCoverdBySolarPower(currentUnits , percentage)
            val noOf320WPanels = calculatePanelsNeeded(averageUnits)
            val noOfInverters = calculateNoofInvertersNeeded(noOf320WPanels)

            val constantValueCost = calculateConstantValues(noOf320WPanels)

            val totalCostWithOutCEBCharges = (noOf320WPanels * PANEL_320W) + (noOfInverters * SOLAR_INVERTER) + constantValueCost

            totalCharges.text = totalCostWithOutCEBCharges.toString()
            cebCharges.text = CEB_CHARGES.toString()
        }
    }

    fun calculateConstantValues( noOfPanels: Double) : Double{
        val mainEarthCost = UNIT_ONE * EARTH_WIRE
        val twoPolarBreakersCost = UNIT_ONE * TWO_POLE_BREAKER
        val wattMeterCost = UNIT_ONE * WATT_METER
        val surgeArresterCost = UNIT_ONE * SURGE_ARRESTER
        val mainWire40MCost = 40 * MAIN_WIRE_1M
        val condutesCost = UNIT_ONE * COUNDUTES_NAILS_SWITCHGEARS
        val transportCost = 2 * TRANSPORT
        val serviceChargesCost = noOfPanels * SERVICE_CHARGE_PER_PANEL

        return mainEarthCost + twoPolarBreakersCost + wattMeterCost + surgeArresterCost +
                mainWire40MCost + condutesCost + transportCost + serviceChargesCost
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {onBackPressed()
            return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
