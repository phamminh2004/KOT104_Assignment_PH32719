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
fun SignupScreen(navControl: NavHostController) {
    var name by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
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
                    .height(100.dp)
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
            text = "WELCOME",
            fontFamily = FontFamily.Serif,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp, bottom = 20.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold


        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 32.dp)
                .shadow(8.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))



                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    modifier = Modifier.fillMaxWidth()

                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff242424),
                        contentColor = Color.White
                    )

                ) {
                    Text(text = "SIGN UP", modifier = Modifier.padding(10.dp))
                }

                Row() {
                    Text(
                        text = "Already have account?",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = Color(0xff808080)
                    )
                    TextButton(
                        onClick = { navControl.popBackStack() },
                    ) {
                        Text(
                            text = "SIGN IN",
                            color = Color(0xff303030),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingupPreview() {
    KOT104_Assignment_PH32719Theme {
        val navControl = rememberNavController()
        SignupScreen(navControl)
    }
}