## Wnioski ze spotkań

### 18.10.2022 -> Nawigacja pomiędzy ekranami Compose'owymi z przekazywaniem danych

Powinniśmy dążyć do rozwiązania, które możliwie odseparuje `NavHosta`od implementacji nawigacji pomiędzy poszczególnymi 
ekranami. Wstępnie, jako wzór wskazane zostało rozwiązanie obecne w CCC. Do rozważenia jest użycie `SafeArgs`, aby 
uniknąć operowania na gołych stringach przy przekazywaniu argumentów. Poza tym, pytanie jak podejść do potrzeby 
serializacji przekazywanych argumentów i czy rozważać w ogóle ten przypadek (może powinniśmy dążyć do przekazywania jak 
'najlżejszych' argumentów). 

Na następnym spotkaniu dopracowujemy najlepszą koncepcję, poza tym przeanalizujemy dzisiejszy "warsztat", ustalimy 
optymalniejszy podział czasu pomiędzy implementację a analizę rozwiązań. Określimy co poszło dobrze, a nad czym trzeba
popracować.