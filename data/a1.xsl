<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a1="http://www.xml.tim14.rs/autorska_prava" version="2.0"
                xmlns:ks="http://www.xml.tim14.rs/korisnici"
>
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
				<title>A-1.pdf</title>
				<style type="text/css">
					body {
						border: 2px solid black;
						margin: 1%;
						width: 43%;
						font-family: Calibri, sans-serif;
						font-style: normal;
						font-weight: normal;
						text-decoration: none;
					}

					.section {
						margin-bottom: 40px;
						margin-left: 7px;
					}

					.header {
						display: flex;
						justify-content: space-between;
						font-weight: bold;
						font-size: 12pt;
					}

					p {
						margin-bottom:auto;
						margin-top: auto;
					}
				</style>
			</head>
			<body>
				<div style="border-bottom: 1px solid black; margin-right: 0px; margin-left: 0px">
					<div class="header">
						<p style="">
							ЗАВОД ЗА ИНТЕЛЕКТУАЛНУ СВОЈИНУ</p>
						<p>ОБРАЗАЦ А-1</p>
					</div>
					<p>Београд, Кнегиње Љубице 5</p>
					<p style="padding: 30px; padding-bottom: 50px; font-weight: bold; font-size: 12pt">ЗАХТЕВ ЗА УНОШЕЊЕ У ЕВИДЕНЦИЈУ И ДЕПОНОВАЊЕ АУТОРСКИХ ДЕЛА</p>
				</div>

				<div class="section">
					1) Подносилац - име, презиме, адреса и држављанство аутора или другог носиоца ауторског права ако је подносилац физичко лице, односно пословно име и седиште носиоца ауторског права ако је подносилац правно лице:<br/>
					<div style="padding: 15px">
						<xsl:choose>
							<xsl:when test="//a1:podnosilac//ks:puno_ime">
								Име <xsl:value-of select="//a1:podnosilac//ks:puno_ime//ks:ime"/> и
								презиме: <xsl:value-of select="//a1:podnosilac//ks:puno_ime//ks:prezime"/><br/>
								Тип држаљанства: <xsl:value-of select="//a1:podnosilac//ks:drzavljanstvo//ks:tip_drzavljanstva"/><br/>
								Јмбг: <xsl:value-of select="//a1:podnosilac//ks:drzavljanstvo//ks:jmbg"/><br/>
								Број пасоша: <xsl:value-of select="//a1:podnosilac//ks:drzavljanstvo//ks:broj_pasosa"/><br/>
							</xsl:when>
							<xsl:otherwise>
								Пословно име: <xsl:value-of select="//a1:podnosilac//ks:poslovno_ime"/><br/>
							</xsl:otherwise>
						</xsl:choose>
						Адреса: <xsl:value-of select="//a1:podnosilac//ks:adresa//ks:ulica"/>
						<xsl:value-of select="//a1:podnosilac//ks:adresa//ks:broj"/>,
						<xsl:value-of select="//a1:podnosilac//ks:adresa//ks:mesto"/>
						<xsl:value-of select="//a1:podnosilac//ks:adresa//ks:postanski_broj"/>
					</div>
					<br/>
					<div style="border: 1px solid black; display: flex; width: 100%">
						<p style="margin-right: auto; margin-left: 0; border-right: 1px solid black; width: 50%">телефон: <xsl:value-of select="//a1:podnosilac//ks:broj_mobilnog_telefona"/></p>
						<p style="margin-right: auto; margin-left: 0; width: 50%">е-mail: <xsl:value-of select="//a1:podnosilac//ks:email"/></p>
					</div>
				</div>

				<div class="section">
					2) Псеудоним или знак аутора, (ако га има):
					<xsl:if test="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:podaci_o_autorima/a1:autor">
						<div style="padding: 15px">
							<xsl:for-each select="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:podaci_o_autorima/a1:autor">
									- <xsl:value-of select="a1:pseudonim"/><br/>
							</xsl:for-each>
						</div>
					</xsl:if>
				</div>

				<div class="section">
					3) Име, презиме и адреса пуномоћника, ако се пријава подноси преко пуномоћника:
					<xsl:if test="a1:punomocnik">
						<div style="padding: 15px">
							Име и презиме: <xsl:value-of select="//a1:punomocnik//ks:puno_ime//ks:ime"/>
							<xsl:value-of select="//a1:punomocnik//ks:puno_ime//ks:prezime"/><br/>
							Адреса:
							<xsl:value-of select="//a1:punomocnik//ks:adresa//ks:ulica"/>
							<xsl:value-of select="//a1:punomocnik//ks:adresa//ks:broj"/>,
							<xsl:value-of select="//a1:punomocnik//ks:adresa//ks:mesto"/>
							<xsl:value-of select="//a1:punomocnik//ks:adresa//ks:postanski_broj"/>
						</div>
					</xsl:if>
				</div>

				<div class="section">
					4) Наслов ауторског дела, односно алтернативни наслов, ако га има, по коме ауторско дело може да се идентификује: <xsl:value-of select="//a1:autorsko_delo//a1:naslov_autorskog_dela"/><br/>
				</div>

				<div class="section">
					5) Подаци о наслову ауторског дела на коме се заснива дело прераде, ако је у питању ауторско дело прераде, као и податак о аутору изворног дела: <br/>
					<xsl:if test="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:izvorno_autorsko_delo">
						<div style="padding: 15px">
							Наслов ауторског дела: <xsl:value-of select="//a1:autorsko_delo//a1:izvorno_autorsko_delo//a1:naslov_izvornog_autorskog_dela"/><br/>
							Име и презиме аутора: <xsl:value-of select="//a1:autorsko_delo//a1:izvorno_autorsko_delo//a1:autor_izvornog_autorskog_dela//a1:licni_podaci//ks:puno_ime//ks:ime"/>  <xsl:value-of select="//a1:autorsko_delo//a1:izvorno_autorsko_delo//a1:autor_izvornog_autorskog_dela//a1:licni_podaci//ks:puno_ime//ks:prezime"/><br/>
						</div>
					</xsl:if>
				</div>

				<div class="section">
					6) Подаци о врсти ауторског дела (књижевно дело, музичко дело, ликовно дело, рачунарски програм и др.): <xsl:value-of select="//a1:autorsko_delo//a1:vrsta_autorskog_dela"/><br/>
				</div>

				<div class="section">
					7) Подаци о форми записа ауторског дела (штампани текст, оптички диск и слично): <xsl:value-of select="//a1:autorsko_delo//a1:forma_zapisa"/><br/>
				</div>

				<div class="section">
					8) Име, адреса и држављанство аутора (групе аутора или коаутора), а ако су у питању један или више аутора који нису живи, имена аутора и године смрти аутора а ако је у питању ауторско дело анонимног аутора навод да је ауторско дело дело анонимног аутора:
					<div style="padding: 15px">
						<xsl:for-each select="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:podaci_o_autorima/a1:autor">
							Име и презиме: <xsl:value-of select="a1:licni_podaci/ks:puno_ime/ks:ime"/> <xsl:value-of select="a1:licni_podaci//ks:puno_ime//ks:prezime"/><br/>
							<xsl:choose>
								<xsl:when test="a1:godina_smrti">
									Година смрти: <xsl:value-of select="a1:godina_smrti"/><br/>
								</xsl:when>
								<xsl:otherwise>
									Адреса: <xsl:value-of select="a1:licni_podaci/ks:adresa/ks:ulica"/>
									<xsl:value-of select="a1:licni_podaci/ks:adresa/ks:broj"/>,
									<xsl:value-of select="a1:licni_podaci/ks:adresa/ks:mesto"/>
									<xsl:value-of select="a1:licni_podaci/ks:adresa/ks:postanski_broj"/><br/>
									Тип држаљанства: <xsl:value-of select="a1:licni_podaci/ks:drzavljanstvo/ks:tip_drzavljanstva"/><br/>
									Јмбг: <xsl:value-of select="a1:licni_podaci/ks:drzavljanstvo/ks:jmbg"/><br/>
									Број пасоша: <xsl:value-of select="a1:licni_podaci/ks:drzavljanstvo/ks:broj_pasosa"/><br/>
								</xsl:otherwise>
							</xsl:choose>
							<xsl:if test="a1:anonimni_autor = 'true'">
								Дело анонимног аутора <br/>
							</xsl:if>
							<br/><br/>
						</xsl:for-each>
					</div>
				</div>

				<div class="section">
					9) Податак да ли је у питању ауторско дело створено у радном односу:
					<xsl:choose>
						<xsl:when test="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:stvoreno_u_radnom_odnosu = 'true'">
							Јесте<br/>
						</xsl:when>
						<xsl:otherwise>
							Није<br/>
						</xsl:otherwise>
					</xsl:choose>
				</div>

				<div class="section">
					10) Начин коришћења ауторског дела или намеравани начин коришћења ауторског дела: <xsl:value-of select="//a1:autorsko_delo//a1:nacin_koriscenja_autorskog_dela"/><br/>
				</div>

				<div class="section">
					12) Прилози који се подносе уз захтев: <xsl:value-of select="//a1:autorsko_delo//a1:primer_autorskog_dela"/><br/>
					<xsl:value-of select="//a1:autorsko_delo//a1:opis_autorskog_dela"/><br/>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>