package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.presentation.theme.InPostRecruitmentTaskTheme

@Composable
fun ShipmentCard(
    number: String,
    status: String,
    sender: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    ShipmentCardContent(
        number = number,
        status = status,
        sender = sender,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
private fun ShipmentCardContent(
    number: String,
    status: String,
    sender: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 20.dp)
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Text("NR PRZESYLKI")
        Text(number)
        Spacer(modifier = Modifier.height(16.dp))
        Text("STATUS")
        Text(status)
        Spacer(modifier = Modifier.height(16.dp))
        Text("NADAWCA")
        Text(sender)
    }
}

@Preview
@Composable
private fun preview_content() {
    InPostRecruitmentTaskTheme {
        Surface {
            ShipmentCardContent(
                number = "16730345345597442248333",
                status = "READY_TO_PICKUP",
                sender = "sender@example.com",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
