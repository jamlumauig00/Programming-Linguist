package jamsxz.programming.linguist.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import jamsxz.programming.linguist.R
import jamsxz.programming.linguist.model.Programming
import jamsxz.programming.linguist.view.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sportsList: List<Programming> = Programming.programmingList(applicationContext)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val fab: FloatingActionButton = findViewById(R.id.fab_detail)

        val adapter = ProgrammingAdapter(sportsList, sportsItemListener)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        fab.setOnLongClickListener {
            Snackbar.make(it, "Click to Take a quiz!", Snackbar.LENGTH_SHORT).show()
            true
        }
    }

    private val sportsItemListener = ProgrammingAdapter.OnClickListener { sports ->
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("Intent to Detail Activity", sports)
        startActivity(intent)
    }
}
