package ru.cyber_eagle_owl.kotlindz3

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.post_item.*
import ru.cyber_eagle_owl.kotlindz3.dto.Post

class MainActivity : AppCompatActivity() {

    private val testPost = Post(
        3234, "CATS", "All your base are belong to us", "1992",
        likeCount = 3,
        commentCount = 1,
        shareCount = 0,
        likedByMe = true,
        commentedByMe = false,
        sharedByMe = false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        likeCountIcon.setOnClickListener { likeAction() }
        commentCountIcon.setOnClickListener { commentAction() }
        sharedCountIcon.setOnClickListener { shareAction() }
        fillPost(testPost)
    }

    private fun fillPost(postModel: Post) {
        with(postModel) {
            createdDate.text = created
            contentText.text = content
            authorTV.text = author
            fillCountText(likeCountText, likeCount, likedByMe)
            fillCountText(commentCountText, commentCount, commentedByMe)
            fillCountText(sharedCountText, shareCount, sharedByMe)
            iconManagement(
                likeCountIcon,
                likedByMe,
                R.drawable.ic_favorite_active_24dp,
                R.drawable.ic_favorite_inactive_24dp
            )
            iconManagement(
                commentCountIcon,
                commentedByMe,
                R.drawable.ic_comment_active_24dp,
                R.drawable.ic_comment_inactive_24dp
            )
            iconManagement(
                sharedCountIcon,
                sharedByMe,
                R.drawable.ic_share_active_24dp,
                R.drawable.ic_share_inactive_24dp
            )
        }
    }

    private fun fillCountText(textView: TextView, count: Long, isTheTextColored: Boolean) {
        if (count == 0L) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
        with(textView) {
            text = count.toString()
            if (isTheTextColored) {
                setTextColor(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        resources.getColor(R.color.colorRed, this@MainActivity.theme)
                    } else {
                        resources.getColor(R.color.colorRed)
                    }
                )
            } else {
                setTextColor(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        resources.getColor(R.color.colorTextSecondary, this@MainActivity.theme)
                    } else {
                        resources.getColor(R.color.colorTextSecondary)
                    }
                )
            }
        }
    }

    private fun iconManagement(
        icon: ImageButton,
        isTheIconActive: Boolean,
        activeIcon: Int,
        inactiveIcon: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon.setImageDrawable(
                resources.getDrawable(
                    if (isTheIconActive) activeIcon else inactiveIcon,
                    this.theme
                )
            )
        } else {
            icon.setImageDrawable(resources.getDrawable(if (isTheIconActive) activeIcon else inactiveIcon))
        }
    }

    private fun likeAction() {
        with(testPost) {
            likedByMe = !likedByMe
            fillPost(this)
        }
    }

    private fun commentAction() {
        with(testPost) {
            commentedByMe = !commentedByMe
            fillPost(this)
        }
    }

    private fun shareAction() {
        with(testPost) {
            sharedByMe = !sharedByMe
            fillPost(this)
        }
    }
}