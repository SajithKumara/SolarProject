package com.primesoft.solarcalculator.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.primesoft.solarcalculator.R
import com.primesoft.solarcalculator.common.Constant
import com.primesoft.solarcalculator.common.readInt
import com.primesoft.solarcalculator.common.saveInt
import kotlinx.android.synthetic.main.activity_planing.*

class PlaningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planing)

        supportActionBar?.title = "Your plan to invest"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val savedPercentage = readInt(Constant.PERCENTAGE_COVERED)
        if (savedPercentage != 0 ){
            seekBar.progress = savedPercentage / 5
        }

        seekbarValue.text = (seekBar.progress * 5).toString()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekbarValue.text = (progress * 5).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        btn_show_plan.setOnClickListener { v ->

            val percentage = seekbarValue.text?.toString()?.toInt()
            percentage?.let {
                saveInt(Constant.PERCENTAGE_COVERED, it)
                startActivity(Intent(this, ConclusionActivity::class.java))
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
