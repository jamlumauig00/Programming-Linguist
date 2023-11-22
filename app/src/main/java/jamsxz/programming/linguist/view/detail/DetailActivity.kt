package jamsxz.programming.linguist.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import jamsxz.programming.linguist.R
import jamsxz.programming.linguist.model.Programming

class DetailActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sports: Programming = intent.getParcelableExtra("Intent to Detail Activity")!!
        val sportsList: List<Programming> = Programming.programmingList(applicationContext)

        viewPager2 = findViewById(R.id.detail_view_pager)
        viewPager2.setPageTransformer(ZoomOutPageTransformer())

        val viewPagerAdapter = DetailViewPagerAdapter(sportsList, this, viewPager2)
        viewPager2.adapter = viewPagerAdapter
        viewPager2.setCurrentItem(sports.id, false)
    }
}