package com.example.composeapp.component.bottomNav


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BottomNavShape(
    private val offset : Float,
    private val circleRadius : Float,
    private val cornerRadius : Float = 50f
): Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            Path().apply {
                moveTo(0f,cornerRadius)
                quadraticBezierTo(0f,0f,cornerRadius,0f)
                lineTo(offset - circleRadius * 1.5f,0f)
                cubicTo(
                    offset - circleRadius, 0f,
                    offset - circleRadius, circleRadius,
                    offset, circleRadius
                )
                cubicTo(
                    offset + circleRadius, circleRadius,
                    offset + circleRadius, 0f,
                    offset + circleRadius * 1.5f, 0f
                )
                lineTo(size.width - cornerRadius, 0f)
                quadraticBezierTo(size.width, 0f, size.width, cornerRadius)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
        )
    }
}

class BarShape(
    private val offset: Float,
    private val circleRadius: Dp,
    private val cornerRadius: Dp,
    private val circleGap: Dp = 6.dp,
) : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(getPath(size, density))
    }

    private fun getPath(size: Size, density: Density): Path {
        val cutoutCenterX = offset
        val cutoutRadius = density.run { (circleRadius + circleGap).toPx() }
        val cornerRadiusPx = density.run { cornerRadius.toPx() }
        val cornerDiameter = cornerRadiusPx * 2

        return Path().apply {
            val cutoutEdgeOffset = cutoutRadius * 1.5f
            val cutoutLeftX = cutoutCenterX - cutoutEdgeOffset
            val cutoutRightX = cutoutCenterX + cutoutEdgeOffset

            moveTo(x = 0F, y = size.height)

            // Top Left Corner
            if (cutoutLeftX > 0) {
                val realLeftCornerDiameter = if (cutoutLeftX >= cornerRadiusPx) cornerDiameter else cutoutLeftX * 2
                arcTo(
                    rect = Rect(0f, 0f, realLeftCornerDiameter, realLeftCornerDiameter),
                    startAngleDegrees = 180.0f, sweepAngleDegrees = 90.0f, forceMoveTo = false
                )
            }

            lineTo(cutoutLeftX, 0f)

            // Lekukan (Cubic Bezier)
            cubicTo(
                x1 = cutoutCenterX - cutoutRadius, y1 = 0f,
                x2 = cutoutCenterX - cutoutRadius, y2 = cutoutRadius,
                x3 = cutoutCenterX, y3 = cutoutRadius,
            )
            cubicTo(
                x1 = cutoutCenterX + cutoutRadius, y1 = cutoutRadius,
                x2 = cutoutCenterX + cutoutRadius, y2 = 0f,
                x3 = cutoutRightX, y3 = 0f,
            )

            // Top Right Corner
            if (cutoutRightX < size.width) {
                val realRightCornerDiameter = if (cutoutRightX <= size.width - cornerRadiusPx) cornerDiameter else (size.width - cutoutRightX) * 2
                arcTo(
                    rect = Rect(size.width - realRightCornerDiameter, 0f, size.width, realRightCornerDiameter),
                    startAngleDegrees = -90.0f, sweepAngleDegrees = 90.0f, forceMoveTo = false
                )
            }

            lineTo(x = size.width, y = size.height)
            close()
        }
    }
}

@Composable
fun Circle(
    modifier: Modifier = Modifier,
    color: Color,
    radius: Dp,
    icon: ImageVector,
    iconColor: Color,
    title: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(radius * 2)
            .shadow(elevation = 8.dp, shape = CircleShape)
            .clip(CircleShape)
            .background(color),
    ) {
        AnimatedContent(
            targetState = icon,
            label = "Bottom bar circle icon",
            transitionSpec = {
                scaleIn(animationSpec = spring(0.6f, Spring.StiffnessLow)) togetherWith
                        scaleOut(animationSpec = spring(0.6f, Spring.StiffnessLow))
            }
        ) { targetIcon ->
            Icon(targetIcon, contentDescription = title, tint = iconColor)
        }
    }
}