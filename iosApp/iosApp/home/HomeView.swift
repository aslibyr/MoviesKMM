import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject var viewModel = HomeViewModel()
 

    let gridColumns: [GridItem] = [
        GridItem(.flexible(), spacing: 16),
        GridItem(.flexible(), spacing: 16)
    ]
    
    var body: some View {
        NavigationStack {
            // ZStack kullanarak tüm arka planı siyaha ayarlayın
            ZStack {
                Color.black // Arka plan rengi
                    .edgesIgnoringSafeArea(.all) // Tüm ekranı kaplamasını sağlar

                ScrollView {
                    LazyVGrid(columns: gridColumns, spacing: 16) {
                        ForEach(viewModel.movies, id: \.id) { movie in
                            NavigationLink(value: movie) {
                                MovieGridItem(movie: movie)
                                    .frame(height: 280)
                                    .cornerRadius(12)
                                    .shadow(color: .black.opacity(0.3), radius: 5, x: 0, y: 5)
                                    .task {
                                        if movie == viewModel.movies.last && !viewModel.isLoading && !viewModel.loadFinished {
                                            await viewModel.loadMovies()
                                        }
                                    }
                            }
                            .buttonStyle(PlainButtonStyle())
                        }

                        if viewModel.isLoading {
                            ProgressView()
                                .frame(maxWidth: .infinity)
                                .padding()
                        }
                    }
                    .padding(.horizontal, 12)
                    .padding(.vertical, 16)
                    .navigationDestination(for: MovieModel.self) { movie in
                        DetailScreen(movie: movie)
                    }
                }
                // ScrollView'in arka plan rengini de siyaha ayarlayın,
                // aksi takdirde kaydırma sırasında beyaz bir alan görünebilir.
                .background(Color.black)
            }
            .navigationTitle("Movies")
            // NavigationStack'in üst çubuğunu siyaha ayarlayın
            .toolbarBackground(
                Color.black,
                for: .navigationBar
            )
            .toolbarBackground(.visible, for: .navigationBar) // Üst çubuğun görünür olmasını sağlar
            // Üst çubuktaki öğelerin (başlık, düğmeler vb.) renk şemasını koyu moda uygun hale getirin
            .toolbarColorScheme(.dark, for: .navigationBar)
        }
        .task {
            await viewModel.loadMovies()
        }
    }
}

