<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p1="http://www.xml.tim14.rs/zahtev_za_priznanje_patenta"
                xmlns:ks="http://www.xml.tim14.rs/korisnici" version="2.0"
>
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
				<title>P-1.html</title>
				<style type="text/css">
					p {
						margin-bottom:auto;
						margin-top: auto;
					}

					body {
						font-family: Arial, sans-serif;
						font-size: 9px;
						background-color: #9e9e9e;
						background-image: url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI1IiBoZWlnaHQ9IjUiPgo8cmVjdCB3aWR0aD0iNSIgaGVpZ2h0PSI1IiBmaWxsPSIjOWU5ZTllIj48L3JlY3Q+CjxwYXRoIGQ9Ik0wIDVMNSAwWk02IDRMNCA2Wk0tMSAxTDEgLTFaIiBzdHJva2U9IiM4ODgiIHN0cm9rZS13aWR0aD0iMSI+PC9wYXRoPgo8L3N2Zz4=);
					}

					.mainContainer {
						padding: 20px;
						border: 1px solid black;
						width: 45%;
						margin-left: auto;
						margin-right: auto;
						margin-top: 20px;
						background-color: white
					}

					table {
						font-family: Arial, sans-serif;
						font-size: 10px;
						border: 2px solid black;
						border-collapse:collapse;
					}

					table.center {
						margin-left: auto;
						margin-right: auto;
						margin-top: 30px;
						width: 100%;
					}

					table.prijava {
						width: 55%;
					}

					table td, table td * {
						vertical-align: top;
					}


				</style>
			</head>
			<body>
				<div class="mainContainer">
					<table class="prijava">
						<tr style="border-bottom: 1px solid black;">
							<td colspan="2" style="border-bottom: 1px solid black;">
								<p style="text-align: center; font-size: 8">
									Попуњава Завод
								</p>
							</td>
						</tr>
						<tr style="border-bottom: 1px solid black;">
							<td style="padding: 5px">
								<p>
									Број пријаве
								</p>
								<p style="font-weight:500; font-size:15; margin-left:80px">П-<xsl:value-of
									select="p1:zahtev_za_priznanje_patenta/p1:prijava/p1:broj_prijave"/>
								</p>
							</td>
						</tr>
						<tr >
							<td style="padding: 5px">
								<p>
									Датум пријема
								</p>
								<p style="font-weight: 500; font-size: 15; margin-left: 60px; text-align: center"><xsl:value-of
									select="p1:zahtev_za_priznanje_patenta/p1:prijava/p1:datum_prijema"/>
								</p>
							</td>
							<td style="border-left: 1px solid black; padding: 5px">
								<p>
									<p>
										Признати датум подношења
									</p>
									<p style="font-weight:500; font-size:15px; text-align: center"><xsl:value-of
										select="p1:zahtev_za_priznanje_patenta/p1:prijava/p1:datum_podnosenja"/>
									</p>
								</p>
							</td>
						</tr>
					</table>

					<div style="margin-top:20px; font-size:10;">
						<p style="padding-bottom: 3px">Република Србија</p>
						<p style="padding-bottom: 3px">Завод за интелектуалну својину</p>
						<p style="padding-bottom: 3px">Кнегиње Љубице број 5</p>
						<p>11000 Београд</p>
					</div>

					<p style="font-size:14px; font-weight:bold; text-align:center; margin-top:50px; margin-bottom:60px">ЗАХТЕВ ЗА ПРИЗНАЊЕ ПАТЕНТА</p>

					<table class="center">
						<body>
							<!-- Naziv pronalaska -->
							<tr>
								<td colspan="3">
									<p style="font-weight:770; margin:8px 0px 3px 8px;">
										Поље број I - НАЗИВ ПРОНАЛАСКА
									</p>
								</td>
							</tr>

							<tr>
								<td colspan="3" style="padding-left:8px; padding-bottom:8px; border-bottom:1px solid black">
									<span>
										* Назив проналаска треба да јасно и сажето изражава суштину проналаска и не сме да садржи измишљене или комерцијалне називе,
										жигове, имена, шифре, уобичајене скраћенице за производе и сл.
									</span>
								</td>
							</tr>

							<tr>
								<td colspan="3" style="padding:8px">
									<p>
										На српском језику: <xsl:value-of
										select="p1:zahtev_za_priznanje_patenta/p1:pronalazak/p1:naziv_pronalaska_rs"/>
									</p>
									<p style="margin-top:5px">
										На енглеском језику: <xsl:value-of
										select="p1:zahtev_za_priznanje_patenta/p1:pronalazak/p1:naziv_pronalaska_eng"/>
									</p>
								</td>
							</tr>

							<!-- Podnosilac -->
							<tr style="border-top:2px solid black; border-bottom:1px solid black">
								<td colspan="2" style="padding:8px" >
									<p style="font-weight:bold">
										Поље број II - ПОДНОСИЛАЦ ПРИЈАВЕ
									</p>
								</td>
								<xsl:if test="//p1:podaci_o_podnosiocu/p1:je_pronalazac = 'true'">
									<td style="padding:8px 0px 8px 10px">
										<p style="font-weight:500; color:green; text-align:center">Подносилац пријаве је и проналазач</p>
									</td>
								</xsl:if>
							</tr>

							<tr>
								<td style="border:1px solid black; padding:5px; width: 33.33%">
									<xsl:choose>
										<xsl:when
											test="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime">
											<p>Име и презиме:</p>
											<p style="margin-top:5px">
												<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime//ks:ime"/>
												<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime//ks:prezime"/>
											</p>
										</xsl:when>
										<xsl:otherwise>
											<p>Пословно име:
												<p style="margin-top:5px">
													<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:poslovno_ime"/>
												</p>
											</p>
										</xsl:otherwise>
									</xsl:choose>
								</td>
								<td style="border:1px solid black; padding:5px; width: 33.33%" >
									<p>Улица и број, поштански број, место и држава:
									</p>
									<p style="margin-top: 5px;">
										Улица<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:ulica"/>
										<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:broj"/>
										<br/>
										<p style="margin-top: 3px">
											<xsl:value-of
												select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:mesto"/>
											,<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:adresa//ks:drzava"/>
										</p>
									</p>
								</td>
								<td style="border-bottom: 1px solid black; width: 33.33%">
									<p>
										<p style="margin:5px">Број телефона:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:broj_mobilnog_telefona"/>
										</p>
									</p>
									<p style="border-top: 1px solid black">
										<p style="margin:5px">Број факса:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:broj_faksa"/>
										</p>
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<p style="margin:5px 5px 0px 5px">Држављанство:
										<xsl:if test="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:puno_ime">
												<xsl:value-of select="//p1:podaci_o_podnosiocu//p1:podnosilac//ks:drzavljanstvo//ks:tip_drzavljanstva"/><br/>
												Јмбг: <xsl:value-of select="//p1:podaci_o_podnosiocu//p1:podnosilac//ks:drzavljanstvo//ks:jmbg"/>;
												Број пасоша: <xsl:value-of select="//p1:podaci_o_podnosiocu//p1:podnosilac//ks:drzavljanstvo//ks:broj_pasosa"/><br/>
										</xsl:if>
									</p>
								</td>
								<td style="border-left: 1px solid black;">
									<p>
										<p style="margin:5px;">Е-пошта:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_podnosiocu/p1:podnosilac//ks:email"/>
										</p>
									</p>
								</td>
							</tr>
						</body>
					</table>
				</div>
				<div class="mainContainer">

							<!-- Pronalazac -->
					<table class="center" style="border:2px solid black; border-collapse:collapse">
						<body>
							<tr style="border-top:2px solid black;">
								<td colspan="2" style="padding:8px">
									<p style="font-weight:bold">
										Поље број III - ПРОНАЛАЗАЧ
									</p>
								</td>
								<xsl:if test="//p1:podaci_o_pronalazacu/p1:ne_zeli_da_bude_naveden = 'true'">
									<td style="padding:8px">
										<p style="font-weight:500; color:red; text-align:center">Проналазач не жели да буде наведен у пријави</p>
									</td>
								</xsl:if>
							</tr>

							<tr>
								<td colspan="3" style="padding-left:10px; padding-bottom:8px">
									<p>
										* Ако сви подносиоци пријаве нису и проналазачи, доставља се изјава подносилаца пријаве о основу стицања права на подношење
										пријаве у односу на проналазаче који нису и подносиоци пријаве и у том случају у поље број III се уносе подаци о свим проналазачим
									</p>
								</td>
							</tr>
							<tr style="border-bottom:1px solid black">
								<td colspan="3" style="padding-left:10px; padding-bottom: 8px">
									<p>
										*Ако проналазач не жели да буде наведен у пријави, потребно је доставити потписану изјаву проналазача да не жели да буде наведен.
									</p>
								</td>
							</tr>

							<tr>
								<td style="border: 1px solid black; padding:8px; width: 33.33%" colspan="1">
									<p>Име и презиме:</p>
									<p style="margin-top: 5px">
										<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:puno_ime//ks:ime"/>
										<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:puno_ime//ks:prezime"/>
									</p>
								</td>
								<td colspan="1" style="border:1px solid black; padding:8px; width: 33.33%">
									<p>Улица и број, поштански број, место и држава:
									</p>
									<p style="margin-top:5px">
										Улица<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:ulica"/>
										<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:broj"/>
										<br/>
										<p style="margin-top: 3px">
											<xsl:value-of
												select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:mesto"/>
											<xsl:if test="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:drzava">
												,<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:adresa//ks:drzava"/>
											</xsl:if>
										</p>
									</p>
								</td>
								<td style="border:1px solid black; width: 33.33%">
									<p>
										<p style="margin:5px">Број телефона:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:broj_mobilnog_telefona"/>
										</p>
									</p>
									<p style="border-top: 1px solid black">
										<p style="margin:5px">Број факса:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:broj_faksa"/>
										</p>
									</p>
									<p style="border-top: 1px solid black; ">
										<p style="margin:5px">Е-пошта:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_pronalazacu/p1:pronalazac//ks:email"/>
										</p>
									</p>
								</td>
							</tr>
						</body>
					</table>

					<!-- Punomocnik -->
					<table class="center" style="border:2px solid black; border-collapse: collapse">
						<body>
							<tr style="border-top:2px solid black;">
								<td style="padding:8px">
									<p style="font-weight:bold">Поље број IV</p>
								</td>
								<xsl:if test="//p1:podaci_o_punomocniku/p1:tip_punomocnika = 'punomocnik_za_zastupanje'">
									<td style="padding:8px">
										<p style="font-weight:bold; color:green">ПУНОМОЋНИК ЗА ЗАСТУПАЊЕ</p>
									</td>
								</xsl:if>
								<xsl:if test="//p1:podaci_o_punomocniku/p1:tip_punomocnika = 'zajedinicki_predstavnik'">
									<td style="padding:8px">
										<p style="font-weight:bold; color:green">ЗАЈЕДНИЧКИ ПРЕДСТАВНИК</p>
									</td>
								</xsl:if>
								<xsl:if test="//p1:podaci_o_punomocniku/p1:punomocnik_za_prijem_pismena = 'true'">
									<td style="padding:8px">
										<p style="font-weight:bold; color:green">ПУНОМОЋНИК ЗА ПРИЈЕМ ПИСМЕНА</p>
									</td>
								</xsl:if>
							</tr>

							<tr>
								<td colspan="3" style="padding-left:8px; padding-bottom:3px">
									<p>
										* Пуномоћник за заступање је лице које по овлашћењу подносиоца пријаве предузима радње у управном поступку у границама пуномоћја
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="padding-left:8px; padding-bottom:3px">
									<p>
										* Пуномоћник за пријем писмена је лице које је подносилац пријаве одредио као лице коме се упућују сва писмена насловљена на
										подносиоца
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="border-bottom:1px solid black; padding-left:8px; padding-bottom:8px">
									<p>
										* Заједничи преставник је подносилац пријаве кога су подносиоци пријаве, у случају да пријаву подноси више лица, одредили да иступа у
										поступку пред органом ако подносиоци нису именовали заједничког пуномоћника за заступање
									</p>
								</td>
							</tr>

							<tr>
								<td style="border:1px solid black; padding:8px; width: 33.33%" colspan="1">
									<xsl:choose>
										<xsl:when
											test="//p1:podaci_o_punomocniku/p1:punomocnik//ks:puno_ime">
											<p >Име и презиме:</p>
											<p style="margin-top:8px">
												<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:puno_ime//ks:ime"/>
												<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:puno_ime//ks:prezime"/>
											</p>
										</xsl:when>
										<xsl:otherwise>
											<p>Пословно име:
												<p style="margin-top:8px">
													<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:poslovno_ime"/>
												</p>
											</p>
										</xsl:otherwise>
									</xsl:choose>
								</td>
								<td colspan="1" style="border:1px solid black; padding:8px; width: 33.33%">
									<p>Улица и број, поштански број, место и држава:
									</p>
									<p style="margin-top:8px">
										Улица<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:ulica"/>
										<xsl:value-of select="'&#160;'"/>
										<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:broj"/>
										<br/>
										<p style="margin-top: 3px">
											<xsl:value-of
												select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:mesto"/>
											<xsl:if test="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:drzava">
												,<xsl:value-of select="'&#160;'"/>
												<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:adresa//ks:drzava"/>
											</xsl:if>
										</p>
									</p>
								</td>
								<td colspan="1" style="width: 33.33%">
									<p>
										<p style="margin:5px">Број телефона:</p>
										<p style="margin:5px">
											<xsl:value-of
												select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:broj_mobilnog_telefona"/>
										</p>
									</p>
									<p style="border-top:1px solid black;">
										<p style="margin:5px">Е-пошта:</p>
										<p style="margin:5px">
											<xsl:value-of select="//p1:podaci_o_punomocniku/p1:punomocnik//ks:email"/>
										</p>
									</p>
								</td>
							</tr>

							<!-- Adresa za dostavljanje -->
							<tr style="border-top:2px solid black; border-bottom:1px solid black">
								<td colspan="3" style="padding:8px">
									<p style="font-weight: bold;">
										Поље број V - АДРЕСА ЗА ДОСТАВЉАЊЕ
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="padding:8px;">
									<p>
										Улица и број, поштански број и место:
										<xsl:if test="//p1:podaci_o_dostavljanju//ks:adresa">
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_dostavljanju//ks:adresa//ks:ulica"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_dostavljanju//ks:adresa//ks:broj"/>
											<br/>
											<xsl:value-of
												select="//p1:podaci_o_dostavljanju//ks:adresa//ks:postanski_broj"/>
											<xsl:value-of select="'&#160;'"/>
											<xsl:value-of select="//p1:podaci_o_dostavljanju//ks:adresa//ks:mesto"/>
										</xsl:if>
									</p>
								</td>
							</tr>

							<!-- Nacin dostavljanja -->
							<tr style="border-top:2px solid black; border-bottom:1px solid black">
								<td style="padding:8px">
									<p style="font-weight: bold;">
										Поље број VI - НАЧИН ДОСТАВЉАЊА
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="padding:8px">
									<p>
										<xsl:choose>
											<xsl:when test="//p1:podaci_o_dostavljanju//p1:nacin_dostavljanja = 'elektronskim_putem'">
												<p style="font-size:10; color:green">Подносилац пријаве је сагласан да Завод врши достављање писмена искључиво електронским путем у форми
													електронског документа (у овом случају неопходна је регистрација на порталу „еУправе”)</p>
											</xsl:when>
											<xsl:otherwise>
												<p style="color:green">Подносилац пријаве је сагласан да Завод врши достављање писмена у папирној форми</p>
											</xsl:otherwise>
										</xsl:choose>
									</p>
								</td>
							</tr>

							<!-- Dopunske prijave -->
							<tr style="border-top:2px solid black;">
								<td style="padding:8px">
									<p style="font-weight: bold;">
										Поље број VII
									</p>
								</td>
								<td colspan="2" style="padding:8px">
									<p>
										<xsl:choose>
											<xsl:when test="//p1:osnovna_prijava">
												<p style="font-weight: bold; color: green"> ДОПУНСКА ПРИЈАВА</p>
											</xsl:when>
											<xsl:otherwise>
												<p style="font-weight: bold; color: green">ИЗДВОЈЕНА ПРИЈАВА</p>
											</xsl:otherwise>
										</xsl:choose>
									</p>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<p>
										<xsl:if test="//p1:osnovna_prijava">
											<p style="border-bottom:1px solid black">
												<p style="margin:8px">Број првобитне пријаве / основне пријаве, односно основног патента:
													<xsl:value-of select="//p1:osnovna_prijava//p1:broj_prijave"/>
												</p>
											</p>
											<p style="margin:8px">Датум подношења првобитнe пријаве / основне пријаве:
												<xsl:value-of select="//p1:osnovna_prijava//p1:datum_podnosenja"/>
											</p>
										</xsl:if>
									</p>
								</td>
							</tr>

							<!-- Ranije prijave -->
							<tr>
								<td colspan="3" style="padding:8px; border-bottom:1px solid black; border-top: 2px solid black">
									<p style="font-weight: bold;">
										Поље број VIII - ЗАХТЕВ ЗА ПРИЗНАЊЕ ПРАВА ПРВЕНСТВА ИЗ РАНИЈИХ ПРИЈАВА:
									</p>
								</td>
							</tr>

							<tr style="border-bottom: 1px solid black">
								<td>
									<p style="margin:10px; text-align: center">
										Датум подношења раније пријаве
									</p>
								</td>
								<td style="border-left: 1px solid black">
									<p style="margin: 10px; text-align: center">
										Број раније пријаве
									</p>
								</td>
								<td style="border-left: 1px solid black">
									<p style="margin: 10px; text-align: center">
										Двословна ознака државе, регионалне или међународне организације
									</p>
								</td>
							</tr>
							<xsl:for-each select="p1:zahtev_za_priznanje_patenta/p1:ranije_prijave/p1:ranija_prijava">
								<tr>
									<td>
										<p style="text-align: center; margin: 10px">
											<xsl:value-of select="p1:datum_podnosenja"/>
										</p>
									</td>
									<td style="border-left: 1px solid black">
										<p style="margin: 10px; text-align: center">
											<xsl:value-of select="p1:broj_prijave"/>
										</p>
									</td>
									<td style="border-left: 1px solid black">
										<p style="margin: 10px; text-align: center">
											<xsl:value-of select="p1:oznaka_drzave"/>
										</p>
									</td>
								</tr>
							</xsl:for-each>
						</body>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>