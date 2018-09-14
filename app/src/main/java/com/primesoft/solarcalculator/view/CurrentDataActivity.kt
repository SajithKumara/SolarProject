package com.primesoft.solarcalculator.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.primesoft.solarcalculator.R
import com.primesoft.solarcalculator.common.Constant.Companion.CURRENT_UNITS
import com.primesoft.solarcalculator.common.saveInt
import com.primesoft.solarcalculator.common.toast
import kotlinx.android.synthetic.main.activity_current_data.*

class CurrentDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_data)


        supportActionBar?.title = "Your Current expense"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_plan_solar.setOnClickListener { v ->
            val units = current_unit_value.text?.toString()?.trim()
            if (units != null){
                saveInt(CURRENT_UNITS, units.toInt())
                startActivity(Intent(this, PlaningActivity::class.java))
            } else {
                toast("Please insert current units before continue",Toast.LENGTH_LONG)
            }

        }
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
