package jamsxz.programming.linguist.view.detail

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jamsxz.programming.linguist.R
import jamsxz.programming.linguist.model.Programming

class DetailViewPagerAdapter(
    private val programmingList: List<Programming>,
    private val activity: Activity,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<DetailViewPagerAdapter.DetailViewHolder>() {

    class DetailViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val toolbar: Toolbar = itemView.findViewById(R.id.detail_toolbar)
        private val banner: ImageView = itemView.findViewById(R.id.detail_image_view)
        //private val title: TextView = itemView.findViewById(R.id.title_detail_text_view)
        private val subtitle: TextView = itemView.findViewById(R.id.subtitle_detail_text_view)
        private val about: TextView = itemView.findViewById(R.id.about_detail_text_view)
        private val pros: TextView = itemView.findViewById(R.id.advantages)
        private val cons: TextView = itemView.findViewById(R.id.disadvantages)

        fun bind(
            programming: Programming
        ) {
            banner.setImageResource(programming.banner)
            //title.text = programming.language
            subtitle.text = programming.description
            about.text = programming.features
            toolbar.title = programming.language
            pros.text = programming.pros
            cons.text = programming.cons
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_detail_adapter, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val programmingsArgs: Programming = programmingList[position]
        holder.bind(programmingsArgs)

        holder.toolbar.setNavigationOnClickListener {
            activity.finish()
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                when (state) {
                    ViewPager2.SCROLL_STATE_DRAGGING -> {
                        holder.toolbar.navigationIcon = null
                    }
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        holder.toolbar.setNavigationIcon(R.drawable.ic_up_button)
                    }
                    ViewPager2.SCROLL_STATE_SETTLING -> {
                        holder.toolbar.navigationIcon = null
                    }
                }
            }
        })
    }

    override fun getItemCount() = programmingList.size
}