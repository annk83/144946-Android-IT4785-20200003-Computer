package annk.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = CalculatorView(findViewById(R.id.primary), findViewById(R.id.secondary))
        arrayOf(
            R.id.l0,
            R.id.l1,
            R.id.l2,
            R.id.l3,
            R.id.l4,
            R.id.l5,
            R.id.l6,
            R.id.l7,
            R.id.l8,
            R.id.l9).forEachIndexed { index, i -> findViewById<Button>(i).setOnClickListener { view.number(index) }; }

        findViewById<Button>(R.id.add).setOnClickListener { view.plus() }
        findViewById<Button>(R.id.mul).setOnClickListener { view.mul() }
        findViewById<Button>(R.id.sub).setOnClickListener { view.minus() }
        findViewById<Button>(R.id.div).setOnClickListener { view.div() }
        findViewById<Button>(R.id.eq).setOnClickListener { view.eq() }
        findViewById<Button>(R.id.dot).setOnClickListener { view.dot() }
        findViewById<Button>(R.id.ce).setOnClickListener { view.clear() }
        findViewById<Button>(R.id.c).setOnClickListener { view.fullClear() }
        findViewById<Button>(R.id.bs).setOnClickListener { view.backspace() }
    }
}