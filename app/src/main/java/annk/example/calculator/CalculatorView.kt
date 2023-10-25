package annk.example.calculator

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.lang.StringBuilder
import java.util.Deque
import java.util.Objects

@SuppressLint("SetTextI18n")
class CalculatorView(private val primary: TextView, private val secondary: TextView) {
    private var wait = false

    fun number(i : Int) {
        if(i<0 || i>9) throw IllegalStateException();
        if(wait) {
            wait = false
            secondary.text = primary.text
            primary.text = ""
        }
        primary.text = primary.text.toString() + i.toString()
    }

    fun plus() {
        wait = false;
        if(!primary.text.endsWith(" "))
            primary.text = primary.text.toString() + " + "
    }

    fun minus() {
        wait = false;
        if(!primary.text.endsWith(" "))
            primary.text = primary.text.toString() + " - "
    }

    fun div() {
        wait = false;
        if(!primary.text.endsWith(" "))
            primary.text = primary.text.toString() + " / "
    }

    fun mul() {
        wait = false;
        if(!primary.text.endsWith(" "))
            primary.text = primary.text.toString() + " * "
    }

    fun pred(ch: Char): Int{
        when(ch) {
            '+', '-' -> return 1
            '*', '/' -> return 2
        }
        return -1
    }

    fun cal(str : CharSequence) : Int {
        val dq = ArrayDeque<Char>()
        val res = StringBuilder()

        for(i in str) {
            if(i==' ') continue
            if(i.isDigit()) res.append(i)
            else {
                res.append("|")
                while(!dq.isEmpty() && pred(i) < pred(dq.last())) {
                    res.append("|").append(dq.last())
                    dq.removeLast()
                }
                dq.addLast(i)
            }
        }
        while (!dq.isEmpty())
            res.append("|").append(dq.removeLast())

        val d = ArrayDeque<Int>()
        val str = res.toString()
        Log.i("app", str)
        for(tok in str.split("|")) {
            if(tok.isEmpty()) continue
            if(tok.equals("+")) d.addLast(d.removeLast() + d.removeLast())
            else if(tok.equals("-")) d.addLast(d.removeLast() - d.removeLast())
            else if(tok.equals("*")) d.addLast(d.removeLast() * d.removeLast())
            else if(tok.equals("/")) d.addLast(d.removeLast() / d.removeLast())
            else d.addLast(tok.toInt())
        }
        return d.last()
    }

    fun eq() {
        primary.text = cal(primary.text).toString()
        wait = true
    }

    fun clear() {
        primary.text = ""
    }

    fun fullClear() {
        primary.text = ""
        secondary.text = ""
    }

    fun backspace() {
        if(primary.text.isEmpty()) return
        if(primary.text.endsWith(" "))
            primary.text = primary.text.subSequence(0, primary.text.length-3)
        else primary.text = primary.text.subSequence(0, primary.text.length-1)
        if(primary.text.isEmpty()) primary.text="0"
    }

    fun dot() {
    }
}