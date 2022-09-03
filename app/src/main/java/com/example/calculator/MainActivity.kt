package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastnum:Boolean = false
    var lastdot:Boolean = false
    private var tvinput:TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput = findViewById(R.id.tv_input)

    }

    fun onDigit(view:View){
        tvinput?.append((view as Button).text)
        lastnum = true
        lastdot = false
    }
    fun onClear(view: View){
        tvinput?.text = ""
    }
    fun onDecimal(view: View){
        if(lastnum && !lastdot){
            tvinput?.append(".")
            lastnum = false
            lastdot = true
        }
    }
    fun onoperator(view:View) {
        tvinput?.text?.let {
            if (lastnum && !isoperatoradded(it.toString())){
                tvinput?.append((view as Button).text)
                lastnum = false
                lastnum = false
            }
        }
    }

    fun onEqual(view:View){
        if(lastnum){
            var tvvalue = tvinput?.text.toString()
            var prefix = ""
            try{
                if(tvvalue.startsWith("-")){
                    prefix= "-"
                    tvvalue = tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val splitval = tvvalue.split("-")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvinput?.text = removezero((one.toDouble() - two.toDouble()).toString())
                }
                else if(tvvalue.contains("+")){
                    val splitval = tvvalue.split("+")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvinput?.text = removezero((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvvalue.contains("x")){
                    val splitval = tvvalue.split("x")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvinput?.text = removezero((one.toDouble() * two.toDouble()).toString())
                }else if(tvvalue.contains("/")){
                    val splitval = tvvalue.split("/")
                    var one = splitval[0]
                    var two = splitval[1]
                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    tvinput?.text = removezero((one.toDouble() / two.toDouble()).toString())
                }


            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private  fun removezero(result: String):String
    {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }
    private  fun isoperatoradded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("x") || value.contains("+") || value.contains("-")
        }
    }
}