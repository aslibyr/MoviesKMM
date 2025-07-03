import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviekmm.domain.model.MovieModel

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier, movie: MovieModel, onClick: (MovieModel) -> Unit
) {
    Card(
        modifier = modifier
            .height(280.dp)
            .clickable { onClick(movie) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        // Box, resim, gradient ve metni üst üste bindirmek için kullanılır.
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart // İçerikleri alta ve sola hizala
        ) {
            // Arka plan resmi
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Metnin okunabilirliğini artırmak için resmin üzerine gelen gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 400f // Gradient'in başlangıç pozisyonu
                        )
                    )
            )

            // Başlık ve yıl bilgisi
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White // Metin rengi beyaz
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.releaseDate.take(4), // Sadece yıl gösterimi
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f) // Metin rengi hafif transparan beyaz
                )
            }
        }
    }
}