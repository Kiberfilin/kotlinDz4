package ru.cyber_eagle_owl.kotlindz3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.event_post_item.*
import kotlinx.android.synthetic.main.post_item.*
import ru.cyber_eagle_owl.kotlindz3.dto.Coordinates
import ru.cyber_eagle_owl.kotlindz3.dto.Event
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

    private val testEvent = Event(

        3234, "CATS", "All your base are belong to us", "1992",
        likeCount = 3,
        commentCount = 1,
        shareCount = 0,
        likedByMe = true,
        commentedByMe = false,
        sharedByMe = false,
        address = "Shimizu, Suginami City, Tokyo, Japan",
        coordinates = Coordinates(35.7135292, 139.6134291)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postLikeCountIcon.setOnClickListener { likeAction() }
        postCommentCountIcon.setOnClickListener { commentAction() }
        postSharedCountIcon.setOnClickListener { shareAction() }
        eventPostLocationIcon.setOnClickListener { locateAction() }
        fillPost(testPost)
        fillEvent(testEvent)
    }

    private fun fillPost(postModel: Post) {
        with(postModel) {
            postCreatedDate.text = created
            postContentText.text = content
            postAuthorTV.text = author
            fillCountText(postLikeCountText, likeCount, likedByMe)
            fillCountText(postCommentCountText, commentCount, commentedByMe)
            fillCountText(postSharedCountText, shareCount, sharedByMe)
            iconManagement(
                postLikeCountIcon,
                likedByMe,
                R.drawable.ic_favorite_active_24dp,
                R.drawable.ic_favorite_inactive_24dp
            )
            iconManagement(
                postCommentCountIcon,
                commentedByMe,
                R.drawable.ic_comment_active_24dp,
                R.drawable.ic_comment_inactive_24dp
            )
            iconManagement(
                postSharedCountIcon,
                sharedByMe,
                R.drawable.ic_share_active_24dp,
                R.drawable.ic_share_inactive_24dp
            )
        }
    }

    private fun fillEvent(eventPostModel: Event) {
        with(eventPostModel) {
            eventPostCreatedDate.text = created
            eventPostContentText.text = content
            eventPostAuthorTV.text = author
            eventPostAddress.text = address
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

    private fun locateAction() {
        val (lat, lng) = testEvent.coordinates
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:$lat,$lng")
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}