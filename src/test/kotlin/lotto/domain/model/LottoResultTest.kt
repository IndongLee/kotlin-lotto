package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoResultTest {
    @Test
    fun `LottoResult는 1등부터 4등까지의 당첨 결과를 모두 보관한다`() {
        val lottoWinningList = listOf(
            LottoWinning(LottoRank.FOURTH, 2),
            LottoWinning(LottoRank.THIRD, 1),
            LottoWinning(LottoRank.SECOND, 1),
            LottoWinning(LottoRank.FIRST, 0)
        )

        val lottoResult = LottoResult(lottoWinningList)

        assertThat(lottoResult.value).isEqualTo(lottoWinningList)
    }

    @Test
    fun `1등부터 4등까지의 당첨 결과 중 빠진 게 있다면 IllegalArgumentException이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoWinningList = listOf(
                LottoWinning(LottoRank.FOURTH, 2),
                LottoWinning(LottoRank.THIRD, 1)
            )

            val lottoResult = LottoResult(lottoWinningList)

            assertThat(lottoResult.value).isEqualTo(lottoWinningList)
        }
    }

    @Test
    fun `한 등수의 당첨 결과가 여러 개 들어갔다면 IllegalArgumentException이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val lottoWinningList = listOf(
                LottoWinning(LottoRank.FOURTH, 2),
                LottoWinning(LottoRank.THIRD, 1),
                LottoWinning(LottoRank.SECOND, 1),
                LottoWinning(LottoRank.FIRST, 0),
                LottoWinning(LottoRank.FIRST, 100)
            )

            val lottoResult = LottoResult(lottoWinningList)

            assertThat(lottoResult.value).isEqualTo(lottoWinningList)
        }
    }

    @Test
    fun `get operator를 통해 적절한 LottoWinning을 가져올 수 있다`() {
        val lottoWinningList = listOf(
            LottoWinning(LottoRank.FOURTH, 2),
            LottoWinning(LottoRank.THIRD, 1),
            LottoWinning(LottoRank.SECOND, 1),
            LottoWinning(LottoRank.FIRST, 0)
        )
        val lottoResult = LottoResult(lottoWinningList)

        val expected = LottoWinning(LottoRank.FOURTH, 2)

        assertThat(lottoResult[LottoRank.FOURTH]).isEqualTo(expected)
    }
}
