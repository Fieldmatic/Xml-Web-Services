<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:p1="http://www.xml.tim14.rs/zahtev_za_priznanje_patenta"
                xmlns:ks="http://www.xml.tim14.rs/korisnici" version="2.0">

	<xsl:template match="/" >
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="patent-page">
					<fo:region-body margin="0.25in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="patent-page">
				<fo:flow flow-name="xsl-region-body" font-family="Arial, sans-serif;" font-size="9">

					<!-- Prijava -->
					<fo:block>
						<fo:table border="2px solid black" border-collapse="collapse">
							<fo:table-column column-width="25%"/>
							<fo:table-column column-width="35%"/>
							<fo:table-body>
								<fo:table-row border-bottom="1px solid black">
									<fo:table-cell number-columns-spanned="2" border-bottom="2px solid black" >
											<fo:block text-align="center" font-size="8">
												Попуњава Завод
											</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row border-bottom="1px solid black">
									<fo:table-cell padding="5px">
											<fo:block>
												Број пријаве
											</fo:block>
											<fo:block font-weight="500" font-size="15" margin-left="80px">П-<xsl:value-of
												select="p1:zahtev_za_priznanje_patenta/p1:prijava/p1:broj_prijave"/>
											</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row >
									<fo:table-cell padding="5px">
										<fo:block>
											Датум пријема
										</fo:block>
										<fo:block font-weight="500" font-size="15" margin-left="60px" text-align="center"><xsl:value-of
											select="p1:zahtev_za_priznanje_patenta/p1:prijava/p1:datum_prijema"/>
										</fo:block>
									</fo:table-cell >
									<fo:table-cell border-left="1px solid black" padding="5px">
										<fo:block>
											<fo:block>
												Признати датум подношења
											</fo:block>
											<fo:block font-weight="500" font-size="15" text-align="center"><xsl:value-of
												select="p1:zahtev_za_priznanje_patenta/p1:prijava/p1:datum_podnosenja"/>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block padding-left="5px" margin-top="20px" font-size="10">Република Србија
					<fo:block>Завод за интелектуалну својину</fo:block>
					<fo:block>Кнегиње Љубице број 5</fo:block>
					<fo:block>11000 Београд</fo:block>
					</fo:block>

					<fo:block font-size="14px" font-weight="bold" text-align="center" margin-top="50px" margin-bottom="40px">ЗАХТЕВ ЗА ПРИЗНАЊЕ ПАТЕНТА</fo:block>

					<fo:block>
						<fo:table border="2px solid black">
							<fo:table-column column-width="33%"/>
							<fo:table-column column-width="33%"/>
							<fo:table-column column-width="33%"/>
							<fo:table-body>
								<!-- Naziv pronalaska -->
								<fo:table-row>
									<fo:table-cell number-columns-spanned="3" padding="8px 0px 8px 10px" border-bottom="1px solid black">
										<fo:block font-weight="bold">
											Поље број I - <fo:inline>НАЗИВ ПРОНАЛАСКА</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell number-columns-spanned="3" padding="5px 0px 5px 10px">
										<fo:block>
											На српском језику: <xsl:value-of
											select="p1:zahtev_za_priznanje_patenta/p1:pronalazak/p1:naziv_pronalaska_rs"/>
										</fo:block>
										<fo:block margin-top="5px">
											На енглеском језику: <xsl:value-of
											select="p1:zahtev_za_priznanje_patenta/p1:pronalazak/p1:naziv_pronalaska_eng"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<!-- Podnosilac -->
								<fo:table-row border-top="2px solid black" border-bottom="1px solid black">
									<fo:table-cell number-columns-spanned="2" padding="8px 0px 8px 10px" >
										<fo:block font-weight="bold">
											Поље број II - <fo:inline>ПОДНОСИЛАЦ ПРИЈАВЕ</fo:inline>
										</fo:block>
									</fo:table-cell>
									<xsl:if test="//p1:podaci_o_podnosiocu/p1:je_pronalazac = 'true'">
										<fo:table-cell padding="8px 0px 8px 10px">
												<fo:block font-weight="500" color="green" text-align="center">Подносилац пријаве је и проналазач</fo:block>
										</fo:table-cell>
									</xsl:if>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell border="1px solid black" number-columns-spanned="1"
									               padding="8px 0px 8px 10px" >
										<xsl:choose>
											<xsl:when
												test="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime">
												<fo:block>Име и презиме:</fo:block>
												<fo:block margin-top="5px">
													<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime//ks:ime"/>
													<xsl:value-of select="'&#160;'"/>
													<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime//ks:prezime"/>
												</fo:block>
											</xsl:when>
											<xsl:otherwise>
												<fo:block>Пословно име:
													<fo:block margin-top="5px">
														<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:poslovno_ime"/>
													</fo:block>
												</fo:block>
											</xsl:otherwise>
										</xsl:choose>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="1" border="1px solid black" padding="5px" >
										<fo:block>Улица и број, поштански број, место и држава:
										</fo:block>
										<fo:block margin-top="5px">
											Улица<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:ulica"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:broj"/>
											<xsl:value-of select="'&#x2028;'"/>
											<xsl:value-of
												select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:mesto"/>
											,<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:drzava"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="1" border="1px solid black">
										<fo:block margin="5px">
											<fo:block>Број телефона:</fo:block>
											<fo:block margin-top="5px">
												<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:broj_mobilnog_telefona"/>
											</fo:block>
										</fo:block>
										<fo:block border-bottom="1px solid black" border-top="1px solid black">
											<fo:block margin="5px">Број факса:</fo:block>
											<fo:block margin="5px">
												<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:broj_faksa"/>
											</fo:block>
										</fo:block>
										<fo:block margin="5px" >
											<fo:block>Е-пошта:</fo:block>
											<fo:block margin-top="5px">
												<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:email"/>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block margin="5px">Држављанство:</fo:block>
										<xsl:if test="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime">
											<fo:block margin="12px">
												Тип држаљанства: <xsl:value-of select="//p1:podaci_o_podnosiocu//p1:podnosilac//ks:drzavljanstvo//ks:tip_drzavljanstva"/><br/>
												Јмбг: <xsl:value-of select="//p1:podaci_o_podnosiocu//p1:podnosilac//ks:drzavljanstvo//ks:jmbg"/><br/>
												Број пасоша: <xsl:value-of select="//p1:podaci_o_podnosiocu//p1:podnosilac//ks:drzavljanstvo//ks:broj_pasosa"/><br/>
											</fo:block>
										</xsl:if>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block margin-top="40px">
						<!-- Pronalazac -->
						<fo:table border="2px solid black" border-collapse="collapse">
							<fo:table-column column-width="33%"/>
							<fo:table-column column-width="33%"/>
							<fo:table-column column-width="33%"/>
							<fo:table-body>
								<fo:table-row border-top="2px solid black" border-bottom="1px solid black">
									<fo:table-cell number-columns-spanned="2" padding="8px 0px 8px 10px">
										<fo:block font-weight="bold">
											Поље број III - <fo:inline>ПРОНАЛАЗАЧ</fo:inline>
										</fo:block>
									</fo:table-cell>
									<xsl:if test="//p1:podaci_o_pronalazacu/p1:ne_zeli_da_bude_naveden = 'true'">
										<fo:table-cell padding="8px 0px 8px 10px">
											<fo:block font-weight="500" color="red" text-align="center">Проналазач не жели да буде наведен у пријави</fo:block>
										</fo:table-cell>
									</xsl:if>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell border="1px solid black" number-columns-spanned="1"
									               padding="8px">
										<fo:block>Име и презиме:</fo:block>
										<fo:block margin-top="5px">
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:puno_ime//ks:ime"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:puno_ime//ks:prezime"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="1" border="1px solid black" padding="8">
										<fo:block>Улица и број, поштански број, место и држава:
										</fo:block>
										<fo:block margin-top="5px">
											Улица<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:ulica"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:broj"/>
											<xsl:value-of select="'&#x2028;'"/>
											<xsl:value-of
												select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:mesto"/>
											<xsl:if test="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:drzava">
												,<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:drzava"/>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="1" border="1px solid black">
										<fo:block margin="8px">
											<fo:block>Број телефона:</fo:block>
											<fo:block>
												<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:broj_mobilnog_telefona"/>
											</fo:block>
										</fo:block>
										<fo:block border-bottom="1px solid black" border-top="1px solid black">
											<fo:block margin="8px">Број факса:</fo:block>
											<fo:block margin="8px">
												<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:broj_faksa"/>
											</fo:block>
										</fo:block>
										<fo:block  margin="8px">
											<fo:block>Е-пошта:</fo:block>
											<fo:block>
												<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:email"/>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>

					<fo:block margin-top="40px">
						<!-- Punomocnik -->
						<fo:table border="2px solid black" border-collapse="collapse">
							<fo:table-column column-width="33%"/>
							<fo:table-column column-width="33%"/>
							<fo:table-column column-width="33%"/>
							<fo:table-body>
								<fo:table-row border-top="2px solid black" border-bottom="1px solid black">
									<fo:table-cell number-columns-spanned="1" padding="8px">
										<fo:block font-weight="bold">Поље број IV</fo:block>
									</fo:table-cell>
									<xsl:if test="//p1:podaci_o_punomocniku/p1:tip_punomocnika = 'punomocnik_za_zastupanje'">
										<fo:table-cell number-columns-spanned="1" padding="8px">
											<fo:block font-weight="bold" color="green">ПУНОМОЋНИК ЗА ЗАСТУПАЊЕ</fo:block>
										</fo:table-cell>
									</xsl:if>
									<xsl:if test="//p1:podaci_o_punomocniku/p1:tip_punomocnika = 'zajedinicki_predstavnik'">
										<fo:table-cell number-columns-spanned="1" padding="8px">
											<fo:block font-weight="bold" color="green">ЗАЈЕДНИЧКИ ПРЕДСТАВНИК</fo:block>
										</fo:table-cell>
									</xsl:if>
									<xsl:if test="//p1:podaci_o_punomocniku/p1:punomocnik_za_prijem_pismena = 'true'">
										<fo:table-cell number-columns-spanned="1" padding="8px">
											<fo:block font-weight="bold" color="green">ПУНОМОЋНИК ЗА ПРИЈЕМ ПИСМЕНА</fo:block>
										</fo:table-cell>
									</xsl:if>
								</fo:table-row>

								<fo:table-row>
									<fo:table-cell border="1px solid black" number-columns-spanned="1" padding="8px">
										<xsl:choose>
											<xsl:when
												test="//p1:podaci_o_punomocniku/p1:punomocnik//ks:puno_ime">
												<fo:block >Име и презиме:</fo:block>
												<fo:block margin-top="8px">
													<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:puno_ime//ks:ime"/>
													<xsl:value-of select="'&#160;'"/>
													<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:puno_ime//ks:prezime"/>
												</fo:block>
											</xsl:when>
											<xsl:otherwise>
												<fo:block>Пословно име:
													<fo:block margin-top="8px">
														<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:poslovno_ime"/>
													</fo:block>
												</fo:block>
											</xsl:otherwise>
										</xsl:choose>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="1" border="1px solid black" padding="8px">
										<fo:block>Улица и број, поштански број, место и држава:
										</fo:block>
										<fo:block margin-top="8px">
											Улица<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:ulica"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:broj"/>
											<xsl:value-of select="'&#x2028;'"/>
											<xsl:value-of
												select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:mesto"/>
											<xsl:if test="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:drzava">
												,<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:drzava"/>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="1" border="1px solid black">
										<fo:block border-bottom="1px solid black">
											<fo:block margin="8px">Број телефона:</fo:block>
											<fo:block margin="8px">
												<xsl:value-of
													select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:broj_mobilnog_telefona"/>
											</fo:block>
										</fo:block>
										<fo:block margin="8px">
											<fo:block>Е-пошта:</fo:block>
											<fo:block margin-top="8px">
												<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:email"/>
											</fo:block>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<!-- Adresa za dostavljanje -->
								<fo:table-row border-top="2px solid black" border-bottom="1px solid black">
									<fo:table-cell number-columns-spanned="3" padding="8px 0px 8px 10px">
										<fo:block font-weight="bold">
											Поље број V - <fo:inline>АДРЕСА ЗА ДОСТАВЉАЊЕ</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="3" padding="8px 0px 8px 10px">
										<fo:block>
											Улица и број, поштански број и место:
											<xsl:if test="//p1:podaci_o_dostavljanju//ks:adresa">
												<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_dostavljanju//ks:adresa//ks:ulica"/>
												<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_dostavljanju//ks:adresa//ks:broj"/>
												<xsl:value-of select="'&#x2028;'"/>
												<xsl:value-of
													select="//p1:podaci_o_dostavljanju//ks:adresa//ks:postanski_broj"/>
												<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_dostavljanju//ks:adresa//ks:mesto"/>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<!-- Nacin dostavljanja -->
								<fo:table-row border-top="2px solid black" border-bottom="1px solid black">
									<fo:table-cell column-number="1" padding="8px 0px 8px 10px">
										<fo:block font-weight="bold">
											Поље број VI - <fo:inline>НАЧИН ДОСТАВЉАЊА</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="3" padding="8px 0px 8px 10px">
										<fo:block>
											<xsl:choose>
												<xsl:when test="//p1:podaci_o_dostavljanju//p1:nacin_dostavljanja = 'elektronskim_putem'">
													<fo:block font-size="10" color="green">Подносилац пријаве је сагласан да Завод врши достављање писмена искључиво електронским путем у форми
														електронског документа (у овом случају неопходна је регистрација на порталу „еУправе”)</fo:block>
												</xsl:when>
												<xsl:otherwise>
													<fo:block color="green">Подносилац пријаве је сагласан да Завод врши достављање писмена у папирној форми</fo:block>
												</xsl:otherwise>
											</xsl:choose>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<!-- Dopunske prijave -->
								<fo:table-row border-top="2px solid black" border-bottom="1px solid black">
									<fo:table-cell number-columns-spanned="1" padding="8px 0px 8px 10px">
										<fo:block font-weight="bold">
											Поље број VII
										</fo:block>
									</fo:table-cell>
									<fo:table-cell number-columns-spanned="2" padding="8px">
										<fo:block>
											<xsl:choose>
												<xsl:when test="//p1:osnovna_prijava">
													<fo:block font-weight="bold" color="green"> ДОПУНСКА ПРИЈАВА</fo:block>
												</xsl:when>
												<xsl:otherwise>
													<fo:block font-weight="bold" color="green">ИЗДВОЈЕНА ПРИЈАВА</fo:block>
												</xsl:otherwise>
											</xsl:choose>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="3">
										<fo:block>
											<xsl:if test="//p1:osnovna_prijava">
												<fo:block border-bottom="1px solid black">
													<fo:block margin="8px 0px 8px 10px">Број првобитне пријаве / основне пријаве, односно основног патента:
														<xsl:value-of select="//p1:osnovna_prijava//p1:broj_prijave"/>
													</fo:block>
												</fo:block>
												<fo:block margin="8px 0px 8px 10px">Датум подношења првобитнe пријаве / основне пријаве:
													<xsl:value-of select="//p1:osnovna_prijava//p1:datum_podnosenja"/>
												</fo:block>
											</xsl:if>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<!-- Ranije prijave -->
								<fo:table-row>
									<fo:table-cell number-columns-spanned="3" padding="8px 0px 8px 10px" border-bottom="1px solid black" border-top="2px solid black">
										<fo:block font-weight="bold">
											Поље број VIII - <fo:inline>ЗАХТЕВ ЗА ПРИЗНАЊЕ ПРАВА ПРВЕНСТВА ИЗ РАНИЈИХ ПРИЈАВА:</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<fo:table-row border-bottom="1px solid black">
									<fo:table-cell>
										<fo:block margin="10px" text-align="center">
											Датум подношења раније пријаве
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border-left="1px solid black">
										<fo:block margin="10px" text-align="center">
											Број раније пријаве
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border-left="1px solid black">
										<fo:block margin="10px" text-align="center">
											Двословна ознака државе, регионалне или међународне организације
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:for-each select="p1:zahtev_za_priznanje_patenta/p1:ranije_prijave/p1:ranija_prijava">
									<fo:table-row>
										<fo:table-cell>
											<fo:block text-align="center" margin="10px">
												<xsl:value-of select="p1:datum_podnosenja"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-left="1px solid black">
											<fo:block margin="10px" text-align="center">
												<xsl:value-of select="p1:broj_prijave"/>
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border-left="1px solid black">
											<fo:block margin="10px" text-align="center">
												<xsl:value-of select="p1:oznaka_drzave"/>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>