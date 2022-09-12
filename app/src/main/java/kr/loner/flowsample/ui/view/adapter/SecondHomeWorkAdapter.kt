package kr.loner.flowsample.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.loner.flowsample.R
import kr.loner.flowsample.data.model.Blog
import kr.loner.flowsample.data.model.BlogType
import kr.loner.flowsample.databinding.ItemBlogBinding

class SecondHomeWorkAdapter(private val blogList: List<Blog>) :
    RecyclerView.Adapter<SecondHomeWorkAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val binding = ItemBlogBinding.bind(v)
        fun bind(blog: Blog) {
            with(binding) {
                tvTitle.text = blog.title
                tvContent.text = blog.content
                tvBlogType.text = if (blog.type == BlogType.Naver) {
                    "네이버 블로그"
                } else {
                    "다음 블로그"
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(blogList[position])
    }

    override fun getItemCount(): Int = blogList.size
}