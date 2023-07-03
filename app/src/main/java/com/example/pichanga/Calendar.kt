package com.example.pichanga

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pichanga.ui.theme.*
import com.mrerror.singleRowCalendar.SingleRowCalendar
import java.util.Date


@Composable
//@Preview(showBackground = true, device = "id:pixel_5")
fun CalendarScreen(navController: NavController){

    var day by remember { mutableStateOf(Date()) }

    var showComposable by remember { mutableStateOf(true) }
    /*SingleRowCalendar(onSelectedDayChange = {
        day = it
    })*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(GrayGeneral_WelcomeScreen)
    ) {
        SingleRowCalendar(
            modifier = Modifier.background(GrayGeneral_WelcomeScreen),
            selectedDayBackgroundColor = Color.Green,
            selectedDayTextColor = Color.White,
            dayNumTextColor = Color.White,
            dayTextColor = Color.White,
            iconsTintColor = Color.White,
            headTextColor = Color.White,
            headTextStyle = MaterialTheme.typography.h5,
            onSelectedDayChange = {
                day = it
                //showComposable = true
                //Toast.makeText(context, "day: "+day, Toast.LENGTH_SHORT).show()
            }
        )
        Spacer(modifier = Modifier.size(20.dp))
        if (showComposable){
            TaskView()
        }
    }
}

@Composable
@Preview
fun TaskView(){
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        itemsIndexed(listOfTasks){index, task ->
            TaskDesign(task = task)
        }
    }
}

@Composable
fun TaskDesign(task : Event){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                GrayCalendar_CalendarScreen,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = task.title,
                color = textColor_CalendarScreen,
                style = MaterialTheme.typography.subtitle1
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 5.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    tint = Color.White,
                    contentDescription = "location",
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = task.site,
                    color = textColor_CalendarScreen
                )
            }
        }
        Column() {
            Text(
                text = task.hora,
                color = textColor_CalendarScreen,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

private val listOfTasks = listOf(
    Event(title = "Wake up", site = "Bedroom", hora = "6:15 AM"),
    Event(title = "Work", site = "Bedroom", hora = "8:20 AM"),
    Event(title = "Home", site = "Dining Room", hora = "5:00 PM"),
    Event(title = "Relax & Read", site = "Bedroom", hora = "8:30 PM"),
    Event(title = "Nigth Ligth", site = "Bedroom", hora = "9:30 PM")
)
