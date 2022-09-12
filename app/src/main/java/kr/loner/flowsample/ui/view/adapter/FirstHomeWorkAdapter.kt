package kr.loner.flowsample.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.loner.flowsample.R
import kr.loner.flowsample.data.model.BlogType
import kr.loner.flowsample.databinding.ItemBlogSelectableBinding
import kr.loner.flowsample.ui.model.UiBlog
import kr.loner.flowsample.ui.viewmodel.FirstHomeWorkUiBlogState

class FirstHomeWorkAdapter(private val firstHomeWorkUiBlogState: FirstHomeWorkUiBlogState) :
    RecyclerView.Adapter<FirstHomeWorkAdapter.ViewHolder>() {
    private var uiBlogGroup = firstHomeWorkUiBlogState.uiBlogGroup
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = ItemBlogSelectableBinding.bind(v)
        fun bind(uiBlog: UiBlog) {
            with(binding) {
                cbSelect.isChecked = uiBlog.isSelected
                tvTitle.text = uiBlog.blog.title
                tvContent.text = uiBlog.blog.content
                tvBlogType.text = if (uiBlog.blog.type == BlogType.Naver) {
                    "네이버 블로그"
                } else {
                    "다음 블로그"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_blog_selectable, parent, false)
        ).apply {
            binding.cbSelect.setOnClickListener {
                val uiBlog = uiBlogGroup.value[adapterPosition]
                firstHomeWorkUiBlogState.selectBlog(
                    uiBlogGroup.value[adapterPosition].copy(
                        blog = uiBlog.blog,
                        isSelected = !uiBlog.isSelected
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(uiBlogGroup.value[position])
    }

    override fun getItemCount(): Int = uiBlogGroup.value.size
}