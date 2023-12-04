import java.io.File

val a = File("day2.input")
// 132
a.readLines().map{it.split(" ")}.filter{b->val(e,f)=b[0].split("-").map{it.toInt()};b[2].filter{it==b[1][0]}.count()in e..f}.count()
// 137
a.readLines().map{it.split(" ")}.filter{a->val(b,c)=a[0].split("-").map{it.toInt()};(a[2][b-1]==a[1][0]).xor(a[2][c-1]==a[1][0])}.count()
