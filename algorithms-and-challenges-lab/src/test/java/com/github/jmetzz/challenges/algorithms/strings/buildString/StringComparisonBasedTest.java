package com.github.jmetzz.challenges.algorithms.strings.buildString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StringComparisonBasedTest {


    private String input;
    private int expected;
    private int priceA;
    private int priceB;


    public StringComparisonBasedTest(String input, Integer expected, Integer priceA, Integer priceB) {
        this.input = input;
        this.expected = expected;
        this.priceA = priceA;
        this.priceB = priceB;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"abcd", 4, 1, 0},
                {"abab", 2, 1, 0},
                {"obgtmuvrmbobvngeirabdienmrdljmobrnmmbudqdcpqmudrojngsidsscmesuerldmqpvmfroalitjfluihmcptkeqvmnqpnubgujgiinbhpalisiconatmuahpikevvllouocaanmjrsbticrhbchebededplcajqismnmqkttcoohdifljshiufncdkflbbeaqjskrqogmqrkmhatmkcttpmqhtdmmmvispkakoecddioldbpjcadplieqvpgogikvikepajtrcsqhmgnguuevosgludqvlnetkkojepuprnpvjqekoprhlepibnesefdempqlpqfsrlvnmeimdbpedlcdoekkftuunenutkjrerdhjltebfsfdnbqoatsnaecrntkvaofiqnptkiurcjledesdugfmgosdmdvjceedcsrrfiorfjulcigbrllrkfmdikvcckurhdlvbtsapisrqihcalfmobbvvgjeolppvskfkhdihglvdjnitlvqbvbicejgqhfjebauurjsjgkqgqttffakuvqiusftdogignjshrtdahtaoaetglrtlphskuechdajqjubgbtvejaudfaiooaktgccaqlirmomefigvklofagsgbhqjjkitgncjokcegosleflbfmopfkjovjhvgsobudibmgfvbclprkrrouvrjhnnqgntnnhlmqcageubdflqisnmbnlnklridtpfnfurgikqotaenjsufqsrdtkbfjqulsbavlgnnkrgckericfrrogeg", 22, 1, 0},
                {"ifgrhioapmrlpqecblnlgcelrqlngbskgijpbklprbqlllalajqdbeikdslmlscmlrediofrfhonopmgnebqfglihcboobfgdpiobsmambjicrqqgarkkgonhesmcndjhhljfepskrenaislplrijcslmjrdhgfdbdalbpprjecloblaadrrksbbbfkmcjbsqkphgrdfhiddmaeppmomnhpiahjnplliqjjbbknphmmnngjlejqplenrkrmrbbrbjasabcfpmffsdfaejqerqdfmpendlqokomemmolebrkdplpbihqpjjnabajipgjgqebambebgikarkfgphddbhbjaigcakigsininqrbgjmjmdgasaeiksgnieopfkhmhspioqsoahrihfmmnnjghfqceoiehoabfpsdsiclcbrqldsbcqrbnqpjlphofslhiokjmsrqdglgjabfeailiamhkcokkmrlscdrkirijqlsjqbiapeofdljgqiqnhejicmjbkhpjgpdocmajfrdfrrdlkokpkflroppiqmbfapmqrrscpsqdhknbhrrbpianjoclqkaijjfnmlfdsemincjjlflmdklipinbklpprjkddhmmmepabmhokbjdjsiggcassqnjsqoeeiircikafeoppoojrhgdbhseeleinkcbemojaoellglkiqefignmpjqsfoimesoespfqqqpllresrcolhsqssbscalhndgkrfqqnhcrescgleifirsqhqmkqiafndckmbsisgippbiedaidjlmcfkmbscidsoblnsobjmdkaejphikbgmprhafogllqopicegsfggrodbsjgaldndfpefbmdgppobiplkikmdhdlraogjlneiogpqkekjmasnncjgsicgibmerq", 19, 1, 0},
                {"diijqqfkffdarceamrbarjcpikjbgcbgnoebapcadmmkqcinrkpqpibedoapoilboqqdaqgpfpejdrljdjjeoihpnrdqqihapndaqlfcpnkjncimhcggdkpfhjndbnolohgchhjboafqqpndfdekbgqmnbdeqgoilnmaagkkgdcehhaljkdgcrimjrcqrahcfejoomrelclonqdiqhhkjhqacghnbqjrieeqmbkggklbclhoirndfnlhgfejhpnhaimmcbporqjchcarocdeflfffikofohrlkgidhqibohlrkbgcnajfekigjpdkgnjqmdkippokoiqeecgkhceqeepdejldmhlerrnpdgdmoccddbhmofbjqdhqfkfjillhbjmomnkbecdbjnfkcgkopgibaioknmrqbgdoffrpgmepcelhhnrpqjqprgqgdkdopdenregfqakhjaecpdbheaidciacdjcipplfqqqbdpnllniloghccfccddcippkrjiqgpcimgfchkhbkijjojbekrapoiefklhpgpcekfnbqakrbpljpogbgnfkqkobmlbhjjdkhpnniqgpingnnmjmlrnpclchrihejnpomikodhaiimrhkjdf", 18, 1, 0},
                {"cahbjpanhfkogsfdoktakmeehqqtsrielcllgrodkrprkcijttmpgufldnfqqpmceshnhbfpuasjhsitfqkldpiesushocuoigoqibfnkgooqdscsbeacteghhdrqhqbrtasnkptakcosesplpgtsoebmmfkmubbffkcdbghoepaknsenrajocttugcdgraakfaqqrqdbdoabtqljsrnfbqjphetajmhmkbhnshkdfqljeokrmckqaqqptgfjqcpkhgteebphkhmiihqkjoqobbsnltipgltohsaupmpgilhnrgopueqqtbhiospteeeekicacegfehhocigkehibjofrfeikkqlqacgqdjnnktnlhbpgpgomhbdfjjsasjbdghukmbblfcuqprfkpaasgmlijrpukdneepnbcdsecjposrqiecplstffbekumiljelsrbhohaeiohmilmdenprfiulcaoallttfaospsoqbrbcesroikosjgebmqfoflrptecmgcqripbidpsubjemeuielnpcanuskdsrajkgtpaglng", 21, 1, 0},
                {"cbabbbababdcadabbcbcbacbbadaadbbdcdcbdaadbcbccccbdbbcbaaabbaacbbaabcbacadcaaabbcacdaaacdcaaccbdadaccdacbabdcbcdacbbddcdbbbcaaadacdaacdbacccbabcbdabbbadaccaaababcaabaccaabacdbcababbbaddccaccddadacccbcbbddcbccbcaadbcbbdcbcadaddbbbadbbccadcbddadcacacdcaacccdbdccacbddacacacdcbaadcbdaacbddcbbcbacbbabdacacccbbaccdccbbccacbdaadcbdcabbcdbbabcbdbddcbddcadbaacbbcdcacadbaadcbbcaddbbcbadddcabadbdbadcabbacaddbbdddbdaacddccdcdaccadadbaacddabbbddacbccbbcaabacaaddccccddaabbcacbccbbdddadaccdadbcabbabdbbcbbdbcdbcbcccdbbcdbdbdc", 4, 1, 0},
        });
    }

    @Test
    public void testStringComparisonBased() {
        assertEquals(expected, new StringComparisonBased().costToGenerate(input, priceA, priceB));
    }

    @Test
    public void testKMPBased() {
        assertEquals(expected, new KMPBased().costToGenerate(input, priceA, priceB));
    }

}
