import SwiftUI
import shared

struct MovieGridItem: View {
    let movie: MovieModel
    
    var body: some View {
        ZStack(alignment: .bottomLeading) {
            AsyncImage(url: URL(string: movie.imageUrl)) { image in
                image
                    .resizable()
                    .aspectRatio(2/3, contentMode: .fill)
            } placeholder: {
                Color.gray.opacity(0.3)
            }
            .frame(maxWidth: .infinity)
            .clipped()
            .cornerRadius(12)
            
            LinearGradient(
                gradient: Gradient(colors: [.black.opacity(0.7), .clear]),
                startPoint: .bottom,
                endPoint: .top
            )
            .cornerRadius(12)
            
            Text(movie.title)
                .font(.title2)
                .fontWeight(.bold)
                .foregroundColor(.white)
                .lineLimit(1)
                .padding(8)
        }
        .frame(height: 280)
        .shadow(radius: 6)
    }
}


struct MovieGridItem_Previews: PreviewProvider {
    static var previews: some View {
        MovieGridItem(
            movie: MovieModel(
                id: 1,
                title: "The Dark Knight",
                description: "Batman fights Joker",
                imageUrl: "https://upload.wikimedia.org/wikipedia/tr/4/40/Batmanlee.png",
                releaseDate: "2008-07-18",
                voteAverage: 9.0,
                voteCount: 1000
            )
        )
        .previewLayout(.sizeThatFits)
        .padding()
        .background(Color.black)
    }
}
