package ru.cyber_eagle_owl.kotlindz3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        eventPostLikeCountIcon.setOnClickListener { likeAction() }
        eventPostCommentCountIcon.setOnClickListener { commentAction() }
        eventPostSharedCountIcon.setOnClickListener { shareAction() }
        fillPost(testPost)
    }

    private fun fillPost(postModel: Post) {
        with(postModel) {
            eventPostCreatedDate.text = created
            eventPostContentText.text = content
            eventPostAuthorTV.text = author
            fillCountText(eventPostLikeCountText, likeCount, likedByMe)
            fillCountText(eventPostCommentCountText, commentCount, commentedByMe)
            fillCountText(eventPostSharedCountText, shareCount, sharedByMe)
            iconManagement(
                eventPostLikeCountIcon,
                likedByMe,
                R.drawable.ic_favorite_active_24dp,
                R.drawable.ic_favorite_inactive_24dp
            )
            iconManagement(
                eventPostCommentCountIcon,
                commentedByMe,
                R.drawable.ic_comment_active_24dp,
                R.drawable.ic_comment_inactive_24dp
            )
            iconManagement(
                eventPostSharedCountIcon,
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
                setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorRed))
            } else {
                setTextColor(ContextCompat.getColor(this@MainActivity, R.color.colorTextSecondary))
            }
        }
    }

    private fun iconManagement(
        icon: ImageButton,
        isTheIconActive: Boolean,
        activeIcon: Int,
        inactiveIcon: Int
    ) {
        icon.setImageResource(if (isTheIconActive) activeIcon else inactiveIcon)
    }

    private fun likeAction() {
        with(testPost) {
            likedByMe = !likedByMe
            if (likedByMe) likeCount++ else likeCount--
            fillPost(this)
        }
    }

    private fun commentAction() {
        with(testPost) {
            commentedByMe = !commentedByMe
            if (commentedByMe) commentCount++ else commentCount--
            fillPost(this)
        }
    }

    private fun shareAction() {
        with(testPost) {
            sharedByMe = !sharedByMe
            if (sharedByMe) shareCount++ else shareCount--
            fillPost(this)
        }
    }
}