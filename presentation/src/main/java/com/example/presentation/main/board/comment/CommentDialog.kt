package com.example.presentation.main.board.comment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.domain.model.Comment
import com.example.presentation.component.FCTextField

@Composable
fun CommentDialog(
    visible: Boolean,
    comments: List<Comment>,
    onDismissRequest: () -> Unit,
    onCloseClick: () -> Unit = {},
    onSendClick: () -> Unit = {},
    onDeleteComment: (Comment) -> Unit
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            var text by remember { mutableStateOf("") }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(0.5f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = "${comments.size} 댓글"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = onCloseClick) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "닫기"
                                )
                            }
                        }
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(count = comments.size) { index ->
                                val comment = comments[index]
                                CommentCard(
                                    modifier = Modifier,
                                    profileImageUrl = comment.profileImageUrl,
                                    username = comment.username,
                                    text = comment.text,
                                    onDeleteComment = {
                                        onDeleteComment(comment)
                                    }
                                )
                            }
                        }
                        Divider()
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            FCTextField(
                                modifier = Modifier.weight(1f),
                                value = text,
                                onValueChange = { text = it }
                            )
                            IconButton(onClick = onSendClick) {
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "전송"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}