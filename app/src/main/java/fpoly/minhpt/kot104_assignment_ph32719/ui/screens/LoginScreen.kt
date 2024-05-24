package fpoly.minhpt.kot104_assignment_ph32719.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fpoly.minhpt.kot104_assignment_ph32719.R
import fpoly.minhpt.kot104_assignment_ph32719.ui.theme.KOT104_Assignment_PH32719Theme

@Composable
fun LoginScreen(navControl: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.group),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(63.dp)
                    .height(80.dp)
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 30.dp)
            )


        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "HELLO !",
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp, bottom = 8.dp),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF909090)


        )
        Text(
            text = "WELCOME BACK",
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp, bottom = 25.dp),
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold


        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 26.dp)
                .shadow(8.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    trailingIcon = {
                        val image = if (passwordVisible) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        }

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = image,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),

                    )

                TextButton(
                    onClick = { },
                    modifier = Modifier.padding(vertical = 10.dp)


                ) {
                    Text(text = "Forgot Password", color = Color(0xFF303030))
                }
                Button(
                    onClick = {
                        navControl.navigate("home") {
                            popUpTo(navControl.graph.startDestinationId) {
                                inclusive = true
                            }
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff242424),
                        contentColor = Color.White
                    )

                ) {
                    Text(text = "Log in", modifier = Modifier.padding(10.dp))
                }

                TextButton(
                    onClick = { navControl.navigate("sign") },
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Text(text = "SIGN UP", color = Color(0xff303030))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    KOT104_Assignment_PH32719Theme {
        val navControl = rememberNavController()
        LoginScreen(navControl)
    }
}