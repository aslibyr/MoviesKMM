import SwiftUI
import shared

struct DetailScreen: View {
    let movie: MovieModel

    var body: some View {
        // The ScrollView allows the content to be larger than the screen.
        ScrollView {
            // This VStack holds all the scrollable content.
            // By giving it a background, we prevent the description
            // from appearing transparently over the image area when scrolling.
            VStack(spacing: 0) {
                // MARK: - Header Image and Title
                ZStack(alignment: .bottomLeading) {
                    // Background image fetched from a URL
                    AsyncImage(url: URL(string: movie.imageUrl)) { image in
                        image
                            .resizable()
                            .scaledToFill() // Fills the entire frame, might crop edges.
                    } placeholder: {
                        // A placeholder view while the image is loading.
                        Rectangle()
                            .fill(Color.gray.opacity(0.3))
                            .overlay(ProgressView())
                    }
                    // Define the frame for the image area.
                    // .clipped() ensures the scaledToFill image doesn't go beyond these bounds.
                    .frame(height: 450)
                    .clipped()

                    // A gradient overlay at the bottom to make text more readable against the image.
                    LinearGradient(
                        gradient: Gradient(colors: [.black, .black.opacity(0)]),
                        startPoint: .bottom,
                        endPoint: .top
                    )

                    // Title and key details, positioned at the bottom of the ZStack.
                    VStack(alignment: .leading, spacing: 8) {
                        Text(movie.title)
                            .font(.largeTitle.bold())
                            .foregroundColor(.white)
                            .shadow(color: .black.opacity(0.5), radius: 3, x: 0, y: 2)

                        HStack(spacing: 16) {
                            Text(movie.releaseDate.prefix(4)) // Display only the year
                            Text("★ \(String(format: "%.1f", movie.voteAverage))")
                                .foregroundColor(.yellow)
                        }
                        .font(.subheadline)
                        .foregroundColor(.white.opacity(0.8))
                    }
                    .padding()
                }

                // MARK: - Description Section
                VStack(alignment: .leading, spacing: 16) {
                    Text("Overview") // "Summary" title for better UI structure
                        .font(.title2)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                    Text(movie.description_)
                        .font(.body)
                        .foregroundColor(.white.opacity(0.9))
                        .lineSpacing(5) // Adds vertical space between lines for readability.
                }
                .padding()
                .frame(maxWidth: .infinity, alignment: .leading) // Ensures the VStack takes up the full width.
            }
            // This is the key fix: giving the content view an opaque background.
            .background(Color.black)
        }
        // Let the content (the image) bleed into the top safe area for a more modern look.
        .ignoresSafeArea(edges: .top)
        // Set the background for the ScrollView itself, visible in bounce areas.
        .background(Color.black.edgesIgnoringSafeArea(.all))
        .tint(.white) // Affects the color of interactive elements like the scroll bar.
     
    }
}

struct DetailView_Preview: PreviewProvider {
    static var previews: some View {
        // Using a NavigationView for a more realistic preview context.
        NavigationView {
            DetailScreen(movie: MovieModel(
                id: 1,
                title: "The Dark Knight",
                description: "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                // Using a higher quality and more reliable image URL for the preview
                imageUrl: "https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                releaseDate: "2008-07-18",
                voteAverage: 9.0,
                voteCount: 1000
            ))
        }
        .preferredColorScheme(.dark)
    }
}

