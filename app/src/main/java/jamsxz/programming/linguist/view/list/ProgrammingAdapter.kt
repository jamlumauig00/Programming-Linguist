package jamsxz.programming.linguist.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jamsxz.programming.linguist.R
import jamsxz.programming.linguist.model.Programming

class ProgrammingAdapter(
    private val programmingsList: List<Programming>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder>() {

    class ProgrammingViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val banner: ImageView = itemView.findViewById(R.id.banner_item_image_view)
        private val title: TextView = itemView.findViewById(R.id.title_item_text_view)
        private val subtitle: TextView = itemView.findViewById(R.id.subtitle_item_text_view)

        fun bind(
            programmings: Programming,
            onClickListener: OnClickListener
        ) {
            banner.setImageResource(programmings.banner)
            title.text = programmings.language
            subtitle.text = programmings.description
            itemView.setOnClickListener {
                onClickListener.onClick(programmings)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammingViewHolder {
        return ProgrammingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProgrammingViewHolder, position: Int) {
        val programmings: Programming = programmingsList[position]
        holder.bind(programmings, onClickListener)
    }

    override fun getItemCount() = programmingsList.size

    class OnClickListener(val clickListener: (programmings: Programming) -> Unit) {
        fun onClick(programmings: Programming) = clickListener(programmings)
    }
}