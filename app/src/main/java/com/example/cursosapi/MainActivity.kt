package com.example.cursosapi


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursosapi.ui.theme.CursosAPITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            CursosAPITheme {

                CursosScreen()
            }
        }
    }
}

@Composable
fun CursosScreen(
    viewModel: CursoViewModel = viewModel()
) {

    val cursos by
    viewModel.cursos.collectAsState()

    val cargando by
    viewModel.cargando.collectAsState()

    val error by
    viewModel.error.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.cargarCursos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Cursos desde Internet",
            style = MaterialTheme
                .typography
                .headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        if (cargando) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment =
                    Alignment.CenterHorizontally,
                verticalArrangement =
                    Arrangement.Center
            ) {

                CircularProgressIndicator()

                Text(
                    text = "Cargando cursos...",
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
            }
        }

        if (error != null) {

            Text(
                text = error
                    ?: "Ha ocurrido un error",
                color = MaterialTheme
                    .colorScheme
                    .error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        if (!cargando && error == null) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),

                contentPadding =
                    PaddingValues(bottom = 16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                items(cursos) { curso ->

                    CursoItem(curso)
                }
            }
        }
    }
}

@Composable
fun CursoItem(curso: Curso) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text =
                    "${curso.id}. ${curso.title}",

                style =
                    MaterialTheme
                        .typography
                        .titleMedium,

                fontWeight =
                    FontWeight.Bold
            )

            Text(
                text = curso.description,

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,

                modifier =
                    Modifier.padding(top = 8.dp)
            )
        }
    }
}